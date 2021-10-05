package com.changlu.course.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @ClassName PostRequestFilter
 * @Author ChangLu
 * @Date 2021/10/5 9:31
 * @Description 后置请求处理器
 */
@Component
public class PostRequestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    /**
     * filter执行顺序，值越小优先级越高
     * 官方推荐使用x-1方式优先排序
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER-1;  //响应过滤器为1000，这里-1表示该过滤器越优先执行
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        Long startTime = (Long) currentContext.get("startTime");
        Long duration = System.currentTimeMillis() - startTime;
        String uri = currentContext.getRequest().getRequestURI();
        System.out.println("uri:"+uri+"，处理时长为："+duration);
        return null;
    }
}