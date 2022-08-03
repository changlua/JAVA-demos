package com.changlu.seatabookservcie.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.changlu.seatabookservcie.pojo.BookModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChangLu
 * @since 2022-08-02
 */
public interface BookService extends IService<BookModel> {

    int bookRemain(Integer id);

    int minusBookRemain(Integer id);

}
