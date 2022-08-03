package com.changlu.seatauserservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.changlu.seatauserservice.pojo.UserModel;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChangLu
 * @since 2022-08-02
 */
public interface UserService extends IService<UserModel> {

    int getUserRemainBook(Integer uid);

    int minusUserBookCount(Integer uid);

}
