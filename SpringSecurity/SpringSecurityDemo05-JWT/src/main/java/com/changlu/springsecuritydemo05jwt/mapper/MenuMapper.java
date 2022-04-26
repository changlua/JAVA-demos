package com.changlu.springsecuritydemo05jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changlu.springsecuritydemo05jwt.domain.pojo.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userid);
}
