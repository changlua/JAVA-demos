package com.changlu.demos;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.net.URLDecoder;

/**
 * @Description: Apache PDFBox案例：读取pdf所有内容
 * @Author: changlu
 * @Date: 1:28 PM
 */
public class Demo1 {
    public static void main(String[] args) throws Exception{
        //读取resources目录下input.pdf文件
        String inputFile = URLDecoder.decode(Demo1.class.getClassLoader().getResource("input.pdf").getFile(), "UTF-8");
        PDDocument pdDocument = PDDocument.load(new File(inputFile));
        //用于读取文本内容
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        //读取pdf中所有的文件
        String fullText = pdfTextStripper.getText(pdDocument);
        System.out.println(fullText);
    }
}
