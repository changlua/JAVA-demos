package com.changlu.register.balance;

import java.util.List;
import java.util.Random;

/**
 * @ClassName RandomLoadBalance
 * @Author ChangLu
 * @Date 6/4/2022 2:05 PM
 * @Description 随机负载均衡
 */
public class RandomLoadBalance implements LoadBalance{
    @Override
    public String balance(List<String> addressList) {
        Random random = new Random();
        int choose = random.nextInt(addressList.size());
        System.out.println(String.format("进行负载均衡策略选择：总共有%s台，选择了第%s台", addressList.size(), choose));
        return addressList.get(choose);
    }
}
