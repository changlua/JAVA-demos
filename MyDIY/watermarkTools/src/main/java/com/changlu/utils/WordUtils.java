package com.changlu.utils;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import java.io.*;

public class WordUtils {

    /**
     * Word转PDF
     * @param filePath 源docx文件目录及名称  示例：C:\Users\93997\Desktop\watermark tools\watermarkTools\src\main\resources\2024-2-8计算机.docx
     * @param outFilePath 输出文件目录及名称 示例：C:\Users\93997\Desktop\watermark tools\watermarkTools\src\main\resources\2024-2-8.pdf
     */
    public static void wordToPdf(String filePath, String outFilePath) {
        //源文件地址
        File inputWord = new File(filePath);
        //导出文件地址
        File outputFile = new File(outFilePath);
        InputStream doc = null;
        OutputStream outputStream = null;
        try {
            doc = new FileInputStream(inputWord);
            outputStream = new FileOutputStream(outputFile);
            IConverter converter = LocalConverter.builder().build();
            //转换docx=>pdf
            boolean flag = converter.convert(doc).as(DocumentType.DOC).to(outputStream).as(DocumentType.PDF).execute();
            if (flag) {
                converter.shutDown();
            }
            doc.close();
            outputStream.close();
            System.out.println("文件名：" + outFilePath + " 转换成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\93997\\Desktop\\watermark tools\\watermarkTools\\src\\main\\resources\\2024-2-8计算机.docx";
        String outFilePath = "C:\\Users\\93997\\Desktop\\watermark tools\\watermarkTools\\src\\main\\resources\\2024-2-8.pdf";
        //word转pdf
        WordUtils.wordToPdf(filePath, outFilePath);
    }

}
