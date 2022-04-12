package com.changlu;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
/**
 * @ClassName MyCodeGenerator
 * @Author ChangLu
 * @Date 3/30/2022 1:59 PM
 * @Description TODO
 */
public class MyCodeGenerator {

    //核心配置
    private static String targetDatabaseName = "test";//目标数据库名
    private static String username = "root";//用户名
    private static String password = "123456";//密码
    private static String[] tihuan_table_prefix = {}; //表替换前缀，可多个
    private static String[] paichu_table_name = {}; //排除指定表，可多个
    private static String referenceModule = "com.changlu"; //当前的引用模块，如com.changlu.demo
    private static String targetModule = "web"; //输出指定文件目录的包名 如：test

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //全局配置（若是多模块的话传入对应模块的工程名！）
        mpg.setGlobalConfig(createGlobalConfig(null));
//        mpg.setGlobalConfig(createGlobalConfig(common));
        //数据库源
        mpg.setDataSource(createDataSourceConfig());
        //包配置
        mpg.setPackageInfo(createPackageConfig());
        //策略配置
        mpg.setStrategy(createStrategyConfig());
        //默认Velocity模板生成
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());  //其他模板Freemarker

        //执行操作
        mpg.execute();
    }
    // 1、全局配置  若是多模块，传入指定父模块名称
    private static GlobalConfig createGlobalConfig(String parentModuleName){
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//获取到当前工程路径地址，若是多模块情况就会有问题
        if(parentModuleName != null){
            projectPath = projectPath + "/"+parentModuleName+ "/src/main/java";
        }else{
            projectPath = projectPath + "/src/main/java";
        }
        gc.setOutputDir(projectPath)
                .setAuthor("ChangLu") //设置作者
                .setOpen(false) //不打开输出目录
                .setSwagger2(true) //添加swagger2注解
                .setBaseResultMap(true) //每个mapper文件中都生成通用结果映射集
                .setFileOverride(true) //下次生成文件时进行覆盖(不设置的话当进行第二次生成就会在同一个目录产生相同的文件)
                .setEntityName("%sModel") //生成实体类文件名，如：%sModel 生成 UserModel
                .setMapperName("%sMapper") //生成dao，这里我们配置生成如 UserMapper
                .setMapperName("%sMapper") //生成mapper.xml文件，这里生成如 UserMapper.xml
                .setServiceName("%sService") //生成service接口
                .setServiceImplName("%sServiceImpl");//生成service实现类
        return gc;
    }

    // 2、数据库源
    private static DataSourceConfig createDataSourceConfig(){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/+"+targetDatabaseName+"+?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername(username)
                .setPassword(password);
        //转换类型  LocalDateTime=>Date
        dsc.setTypeConvert((globalConfig, fieldType) -> {
            String t = fieldType.toLowerCase();
            if(t.contains("datetime")){   //若是匹配到数据库类型为"datetime"，就返回Date类型（转为Java的）
                return DbColumnType.DATE;
            }
            //其它字段采用默认转换（非mysql数据库可以使用其它默认的数据库转换器）
            return new MySqlTypeConvert().processTypeConvert(globalConfig,fieldType);
        });
        return dsc;
    }

    //3、包配置
    private static PackageConfig createPackageConfig(){
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(targetModule) //生成的模块名，也就是生成目标指定文件名的路径下
                .setParent(referenceModule); //父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        return pc;
    }

    //4、策略配置
    private static StrategyConfig createStrategyConfig(){
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel) //表名生成策略：下划线转驼峰，如pms_pro=>PmsPro
                .setColumnNaming(NamingStrategy.underline_to_camel) //字段名生成策略，同上，如last_name=>lastName
                .setEntityLombokModel(true) //支持lombok注解
                .setTablePrefix(tihuan_table_prefix) //设置表的替换前缀：这里就是起到过滤作用
                .setEntityTableFieldAnnotationEnable(true) //生成表、字段映射注解
                .setExclude(paichu_table_name); //排除指定表
        return strategy;
    }

}
