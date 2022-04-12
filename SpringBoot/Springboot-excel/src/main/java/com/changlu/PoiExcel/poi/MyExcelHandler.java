package com.changlu.PoiExcel.poi;

/**
 * @ClassName MyExcelHandler
 * @Author ChangLu
 * @Date 4/4/2022 12:39 PM
 * @Description 自定义的excel适配器
 */
public class MyExcelHandler implements ExcelHandlerAdapter{


    @Override
    public Object format(Object value, String[] args) {
        return "666";
    }
}
