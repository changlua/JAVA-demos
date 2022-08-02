package com.changlu.sentineluserservice.feign;

import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: changlu
 * @Date: 11:22 AM
 */
@Component
public class UserClientFallback implements UserBookFeign{
    @Override
    public String getUserLikeBook(Long id) {
        return "熔断替代方案";
    }
}
