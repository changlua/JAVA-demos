package com.changlu.sentinelbookservice.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: changlu
 * @Date: 7:19 PM
 */
@Service
public class BookServiceImpl implements BookService{

    //指定blockHandler，也就是被限流之后的替代解决方案，这样就不会使用默认的抛出异常的形式了
    @SentinelResource(value = "getUserLikeBook", blockHandler = "blocked")
    @Override
    public String getUserLikeBook(Long userId) {
        String book = "哈利波特";
        if (userId != 1) {
            book = "西游记";
        }
        return book;
    }

    //替代方案，注意参数和返回值需要保持一致(会一并传入进来)，并且参数最后还需要额外添加一个BlockException
    public String blocked(Long userId, BlockException e) {
        return "限流书籍";
    }
}
