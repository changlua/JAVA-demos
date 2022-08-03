package com.changlu.seataborrowservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.changlu.seataborrowservice.feign.BorrowBookFeign;
import com.changlu.seataborrowservice.feign.BorrowUserFeign;
import com.changlu.seataborrowservice.mapper.BorrowMapper;
import com.changlu.seataborrowservice.pojo.BorrowModel;
import com.changlu.seataborrowservice.service.BorrowService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, BorrowModel> implements BorrowService {

    @Resource
    private BorrowMapper borrowMapper;

    @Autowired
    private BorrowBookFeign borrowBookFeign;

    @Autowired
    private BorrowUserFeign borrowUserFeign;

    @GlobalTransactional
    @Override
    public Boolean borrow(Integer uid, Integer bookId) {
        //1、判断图书与用户是否都支持借阅
        if (borrowBookFeign.bookRemain(bookId) < 0) {
            throw new RuntimeException("该图书库存不足，无法借阅！");
        }
        if (borrowUserFeign.getUserRemainBook(uid) < 1){
            throw new RuntimeException("该用户借阅图书数量已上限！");
        }
        //2、扣减图书库存数量
        if (borrowBookFeign.minusBookRemain(bookId) < 1) {  //book-service服务修改数据
            throw new RuntimeException("扣减图书数量失败！");
        }
        //3、添加图书用户借阅记录
        if (borrowMapper.getBorrow(uid, bookId) != null) {
            throw new RuntimeException("用户已借阅该图书！");
        }
        if (borrowMapper.addBorrow(uid, bookId) < 1) {  //本身服务新增数据
            throw new RuntimeException("图书借阅失败！");
        }
        //4、用户自己本身借阅数量-1
        if (borrowUserFeign.minusUserBookCount(uid) < 1) {  //user-service服务修改数据
            throw new RuntimeException("用户借阅书籍数量更新有误！");
        }
        return true;
    }
}
