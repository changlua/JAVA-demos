package com.changlu.seatauserservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.changlu.seatauserservice.mapper.UserMapper;
import com.changlu.seatauserservice.pojo.UserModel;
import com.changlu.seatauserservice.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int getUserRemainBook(Integer uid) {
        return userMapper.getUserRemainBook(uid);
    }

    @Override
    public int minusUserBookCount(Integer uid) {
        return userMapper.minusUserBookCount(uid);
    }
}
