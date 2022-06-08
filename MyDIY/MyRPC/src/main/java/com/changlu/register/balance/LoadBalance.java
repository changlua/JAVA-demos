package com.changlu.register.balance;

import java.util.List;

/**
 * @ClassName LoadBalance
 * @Author ChangLu
 * @Date 6/4/2022 2:03 PM
 * @Description 负载均衡策略接口
 */
//核心：根据多个服务器地址来进行负载均衡策略选择
public interface LoadBalance {
    String balance(List<String> addressList);
}
