package com.changlu.PoiExcel.controller;

import com.changlu.PoiExcel.poi.ExcelUtil;
import com.changlu.common.mapper.SysUserMapper;
import com.changlu.common.pojo.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName POIExcelController
 * @Author ChangLu
 * @Date 4/4/2022 9:30 AM
 * @Description POI工具包控制器
 */
@RestController
@RequestMapping("/poi/list")
public class POIExcelController {

    @Resource
    private SysUserMapper sysUserMapper;

    @GetMapping
    private void list(HttpServletResponse response){
        List<SysUser> list = sysUserMapper.selectList(null);
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        //sheetname指的是数据名称，表格名字为
        util.exportExcel(response, list, "数据");
    }

}
