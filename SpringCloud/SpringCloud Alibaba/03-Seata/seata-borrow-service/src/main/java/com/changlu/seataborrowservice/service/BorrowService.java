package com.changlu.seataborrowservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.changlu.seataborrowservice.pojo.BorrowModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChangLu
 * @since 2022-08-02
 */
public interface BorrowService extends IService<BorrowModel> {

    Boolean borrow(Integer uid, Integer bookId);
}
