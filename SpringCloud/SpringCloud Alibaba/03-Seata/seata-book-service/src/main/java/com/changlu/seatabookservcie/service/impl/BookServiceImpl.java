package com.changlu.seatabookservcie.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.changlu.seatabookservcie.mapper.BookMapper;
import com.changlu.seatabookservcie.pojo.BookModel;
import com.changlu.seatabookservcie.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChangLu
 * @since 2022-08-02
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, BookModel> implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public int bookRemain(Integer id) {
        return bookMapper.bookRemain(id);
    }

    @Override
    public int minusBookRemain(Integer id) {
        return bookMapper.minusBookRemain(id);
    }
}
