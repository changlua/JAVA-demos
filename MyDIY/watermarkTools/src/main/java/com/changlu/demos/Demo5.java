package com.changlu.demos;

import com.changlu.Main;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;

import java.io.File;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Description: Apache PDFBox案例：将图片缩小25%后插入到右上角。
 * @Author: changlu
 * @Date: 1:38 PM
 */
public class Demo5 {
    public static void main(String[] args) throws Exception{
        //读取resources目录下input.pdf文件
        InputStream is = Main.class.getClassLoader().getResourceAsStream("input.pdf");
        PDDocument pdDocument = PDDocument.load(is);

        //遍历原先的pdf文档
        for (PDPage page : pdDocument.getPages()) {
            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();
            //添加图片水印
            //创建一个水印内容流
            PDPageContentStream imageContentStream = new PDPageContentStream(pdDocument, page, PDPageContentStream.AppendMode.APPEND, true, true);
            // 创建图像对象
//            PDImageXObject image = PDImageXObject.createFromFile("C:\\Users\\93997\\Desktop\\watermark tools\\watermarkTools\\src\\main\\resources\\images\\ConsultationGroupQRCode.jpg", pdDocument);
            String pictureFile = URLDecoder.decode(Main.class.getClassLoader().getResource(File.separator + "images" + File.separator + "ConsultationGroupQRCode.jpg").getFile(), "UTF-8");
            PDImageXObject image = PDImageXObject.createFromFile(pictureFile, pdDocument);
            // 计算图像的宽度和高度（缩小比例为0.25）
            float imageWidth = (float) (image.getWidth() * 0.25);
            float imageHeight = (float) (image.getHeight() * 0.25);
            //具体图片位置
            float imageX = pageWidth - imageWidth - 10;
            float imageY = pageHeight - imageHeight - 10;

            // 在指定位置绘制图像
            imageContentStream.drawImage(image, imageX, imageY, imageWidth, imageHeight);
            imageContentStream.close();
        }

        //目标目录 Thread.currentThread().getContextClassLoader().getResource("").getPath()  当前工程目录路径
        String targetPDFPath = "F:\\00核心知识、成果、视频产出区\\技术视频\\2024.2.15 自制默默学打水印工具 watermark tools\\watermarkTools\\src\\main\\resources\\output.pdf";
        File outputFile = new File(targetPDFPath);
        // 若是文件存在先进行删除
        Files.deleteIfExists(Paths.get(outputFile.toURI()));
        // 保存修改后的文档
        pdDocument.save(outputFile);
        System.out.println("转换任务：" + targetPDFPath + " 成功！");
        // 关闭文档
        pdDocument.close(); // 关闭文档
    }
}
