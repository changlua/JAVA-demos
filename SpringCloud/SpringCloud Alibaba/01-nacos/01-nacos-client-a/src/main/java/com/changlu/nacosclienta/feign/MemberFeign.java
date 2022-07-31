package com.changlu.nacosclienta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Author: changlu
 * @Date: 9:17 PM
 */
@FeignClient(value = "nacos-client-b")
public interface MemberFeign {

    @GetMapping("/info")
    String info();

}
