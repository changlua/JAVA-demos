package com.changlu.springsecuritydemo2.config;

import com.changlu.springsecuritydemo2.Handler.MyAuthenticationFailureHandler;
import com.changlu.springsecuritydemo2.Handler.MyAuthenticationSuccessHandler;
import com.changlu.springsecuritydemo2.Handler.MyLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

/**
 * @ClassName WebSecruityConfig
 * @Author ChangLu
 * @Date 3/22/2022 4:19 PM
 * @Description 自定义security
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //方式一：默认的全局 AuthenticationManager中的userDetailsService自动工厂加载(框架自动读取并注入到AuthenticationManager)
    //Spring security中的AuthenticationManager会默认在工厂中找是否有UserDetailsService，有的话就会获取并配置
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        //若是密码无加密的话，明文密码前面必须带有{noop}
        userDetailsService.createUser(User.withUsername("ccc").password("{noop}789").roles("admin,user").build());
        return userDetailsService;
    }

    //方式二：默认的全局 AuthenticationManager方法中注入springsecurity中（这种是通过自己手动替换掉userDetailsService）
    //Springboot对 security默认配置中 在工厂中默认创建 的AuthenticationManager
    //这种注入的方式会覆盖掉原本Security默认管理器里的userDetailsService（也就是自动生成的或都配置文件配置的用户）
//    @Autowired
//    public void initialize(AuthenticationManagerBuilder builder) throws Exception {
//        System.out.println("springboot默认配置:"+ builder);//DefaultPasswordEncoderAuthenticationManagerBuilder
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//        userDetailsService.createUser(User.withUsername("aaa").password("{noop}123").roles("admin,user").build());
//        userDetailsService.createUser(User.withUsername("bbb").password("{noop}456").roles("admin,user").build());
//        builder.userDetailsService(userDetailsService);
//    }

    //方式三：自定义AuthenticationManager，其并没有在工厂中暴露出来（推荐！！！）
    //自定义的AuthenticationManager（ProviderManager）会替换到原本DefaultPasswordEncoderAuthenticationManagerBuilder（也就是Springboot中默认的那个）
    //auth.userDetailsService：这种方式是修改的自定义认证管理器里的认证数据源
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("自定义认证管理器"+ auth);//WebSecurityConfigurerAdapter
        auth.userDetailsService(userDetailsService());//这里修改的是DaoAuthenticationProvider的认证数据源
        //扩展：若是想要自定义一些Provider，也可以通过如下方式来添加自定义：
        //auth.authenticationProvider(new DaoAuthenticationProvider());
    }

    //作用：用来自定义AuthenticationManager在工厂中进行暴露，此时就可以在任何位置注入
    //目的就是用来通过在service层中通过注入方式得到该自定义管理器对象来进行认证：
    /**
        @Resource
        private AuthenticationManager authenticationManager;   //实际业务：authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password))
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        //构建时会在会authenticationManager初始化构造去判断时本地创建(自定义)或springboot默认创建的
        //构建的AuthenticationManagerDelegator（管理器），是实现AuthenticationManager的一个内部实现类
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        HttpMethod.GET,
                        "/login.html",
                        "/error.html"
                ).permitAll() //放行自定义配置的登录页面
//                .mvcMatchers("/login.html").permitAll()
//                .mvcMatchers("/error.html").permitAll()
                .anyRequest().authenticated()
                .and()
                //配置form登录的登录页面，初始状态登录地址若是访问/login其会302重定向到我们自定义页面
                .formLogin()
                    //配置FormLogin页请求
                    .loginPage("/login.html")
                        //修改security给我们提供的接口登录请求url、body参数
                            .loginProcessingUrl("/doLogin")   //security默认给我们的form登录接口时/login，我们可以修改接口名为/doLogin,实际走的接口依旧是security中的
                            .usernameParameter("uname")       //修改security中的登录body中的用户名与密码：默认是username、password
                            .passwordParameter("passwd")
                        //配置成功跳转的页面：默认是跳转登录页前的url
                        //.successForwardUrl("/hello")        //1、forward，浏览器地址栏不会变，直接返回/hello接口下内容。(地址栏上是/doLogin进行请求的url)
                        //.defaultSuccessUrl("/hello",true)   //2、redirect，浏览器地址栏会变(若是加第二个参数，则不变)
                        .successHandler(new MyAuthenticationSuccessHandler())  //3、自定义成功登录返回内容(前后端分离)
                        //配置失败跳转的页面：默认路径为http://localhost:8080/login.html?error
                        //.failureForwardUrl("/error.html")  //1、forward（request作用域），浏览器地址不变。(地址栏上是/doLogin进行请求的url)
                        //.failureUrl("/login.html")          //2、redirect（Session作用域，可获取异常信息div th:text="${session.SPRING_SECURITY_LAST_EXCEPTION}"></div>），浏览器地址变为/login.html，登录失败按理说是要跳转到登录页的。
                        .failureHandler(new MyAuthenticationFailureHandler()) //3、自定义登录失败返回内容(前后端分离)
                .and()
                    //配置logout接口：默认是/logout，GE请求，默认会自动redirect/到自己配置的login.html(登录页面)
                    .logout()
                        //1、修改原本security给我们提供的接口url、请求方法（本质走的还是security中的接口）
                        //.logoutUrl("/logout");
                        .logoutRequestMatcher(new OrRequestMatcher(
                                new AntPathRequestMatcher("/aa","GET"),
                                new AntPathRequestMatcher("/bb","POST")
                        ))
                         //修改配置（这些都是security的logout注销接口自带的，可以不手动配）
                        //.invalidateHttpSession(true)  //默认 会话失效
                        //.clearAuthentication(true)   //默认 清楚认证标记
                        //2、注销登录 成功之后跳转页面
                        //.logoutSuccessUrl("/error.html");
                        //3、注销登录 成功之后处理
                       .logoutSuccessHandler(new MyLogoutSuccessHandler())
                .and()
                    .csrf().disable();//禁止 csrf 跨站请求保护
    }
}