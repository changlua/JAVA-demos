package com.changlu.register.balance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName RoundLoadBalance
 * @Author ChangLu
 * @Date 6/4/2022 2:06 PM
 * @Description 轮询负载均衡
 */
public class RoundLoadBalance implements LoadBalance{

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public String balance(List<String> addressList) {
        int choose = count.getAndDecrement();//获取到最新的值
        choose = choose % addressList.size();
        System.out.println(String.format("进行负载均衡策略选择：总共有%s台，选择了第%s台", addressList.size(), choose));
        return addressList.get(choose);
    }
}
