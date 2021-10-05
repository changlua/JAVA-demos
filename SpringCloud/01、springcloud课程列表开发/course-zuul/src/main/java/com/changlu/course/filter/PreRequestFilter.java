package com.changlu.course.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @ClassName PreRequestFilter
 * @Author ChangLu
 * @Date 2021/10/5 9:26
 * @Description 前置请求过滤器
 */
@Component
public class PreRequestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //过滤器类型
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //过滤器顺序
        return 0;  //0-1000 从小到大顺序依次执行，这里表示第一个执行
    }

    @Override
    public boolean shouldFilter() {
        return true;  //是否启动过滤器
    }

    //具体过滤器中执行的方法
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        currentContext.set("startTime",System.currentTimeMillis());
        System.out.println("前缀过滤器pre已经记录时间");
        return null;
    }
}