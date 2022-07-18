package com.changlu.myhystrix.hystrix.aspect;

import com.changlu.myhystrix.hystrix.HystrixPlus;
import com.changlu.myhystrix.hystrix.model.HystrixStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description: 熔断器切面
 * @Author: changlu
 * @Date: 10:00 AM
 */
@Component
@Aspect
public class HystrixAspect {

    //切面表达式
//    public static final String POINT_COT = "execution (* com.changlu.myhystrix.controller.CustomerController.customerRent(..))";

    //定义一个断路器Map
    private static Map<String, HystrixPlus> hystrixMap = new HashMap<>();

    static {
        hystrixMap.put("rent-car-service", new HystrixPlus());
    }

    //随机器工具
    public static ThreadLocal<Random> randomThreadLocal = ThreadLocal.withInitial(()->new Random());

    //根据注解来进行切面处理
    @Around(value = "@annotation(com.changlu.myhystrix.hystrix.anno.MyHystrix)")
    public Object hystrixAround(ProceedingJoinPoint joinPoint) {
        //结果集
        Object res = null;
        //根据当前的服务名来获取到对应的断路器
        HystrixPlus hystrix = hystrixMap.get("rent-car-service");
        HystrixStatus status = hystrix.getStatus();
        switch (status) {
            case CLOSE:
                try {
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    //进行计数，并且响应结果
                    hystrix.addFailCount();
                    return "熔断器返回结果";
                }
            case OPEN://打开状态，表示不能调用
                return "熔断器返回结果";
            case HALF_OPEN:
                Random random = randomThreadLocal.get();
                int num = random.nextInt(5);//[0-4]
                //方便回收
                randomThreadLocal.remove();
                //放行部分流量
                if (num == 1) {
                    try {
                        res = joinPoint.proceed();
                        //调用成功,断路器关闭
                        hystrix.setStatus(HystrixStatus.CLOSE);
                        //进行唤醒清理程序
                        synchronized (hystrix.getLock()) {
                            hystrix.getLock().notifyAll();
                        }
                        return res;
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                        return "熔断器返回结果";
                    }
                }
            default:
                return "熔断器返回结果";
        }
    }

}
