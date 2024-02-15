package com.changlu.demos;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.InputStream;

/**
 * @Description: Apache PDFBox案例：读取所有页内容（分页）
 * @Author: changlu
 * @Date: 1:37 PM
 */
public class Demo2 {
    public static void main(String[] args) throws Exception{
        //读取resources目录下input.pdf文件
        InputStream is = Demo2.class.getClassLoader().getResourceAsStream("input.pdf");
        PDDocument pdDocument = PDDocument.load(is);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        //读取所有的分页
        for (int i = 1; i <= pdDocument.getNumberOfPages(); i++) {
            //设置起始-结束页  这里设置指定某页
            pdfTextStripper.setStartPage(i);
            pdfTextStripper.setEndPage(i);
            //读取每一页
            String pageText = pdfTextStripper.getText(pdDocument);
            System.out.println(String.format("第%s页读取内容：", i));
            System.out.println(pageText);
        }
    }
}
