package com.changlu.demos;

import com.changlu.utils.WordUtils;
import java.io.UnsupportedEncodingException;

/**
 * @Description: Documents4j案例：word转PDF实现
 * @Author: changlu
 * @Date: 2:18 PM
 */
public class Demo6 {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        String originPath = URLDecoder.decode(Main.class.getClassLoader().getResource("input.docx").getFile(), "UTF-8"); //获取到的是target下的类目录
        String originPath = "F:\\00核心知识、成果、视频产出区\\技术视频\\2024.2.15 自制默默学打水印工具 watermark tools\\watermarkTools\\src\\main\\resources\\input.docx";
        String targetPath = originPath.replace("input.docx", "output.pdf");
        //docx转为pdf文件
        WordUtils.wordToPdf(originPath, targetPath);
    }
}
