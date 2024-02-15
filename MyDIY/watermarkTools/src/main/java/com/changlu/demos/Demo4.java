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
 * @Description: Apache PDFBox案例：对pdf每页都添加上旋转45°水印。
 * @Author: changlu
 * @Date: 1:38 PM
 */
public class Demo4 {
    public static void main(String[] args) throws Exception{
        //读取resources目录下input.pdf文件
        InputStream is = Main.class.getClassLoader().getResourceAsStream("input.pdf");
        PDDocument pdDocument = PDDocument.load(is);

        //自定义字体 C:\Users\93997\Desktop\watermark tools\watermarkTools\target\classes\ttfs
        //URLDecoder.decode() 方法来解码 URL 编码的路径，将 %20 转换回空格
        String fontFile = URLDecoder.decode(Main.class.getClassLoader().getResource(File.separator + "ttfs" + File.separator + "Alibaba_PuHuiTi_2.0_65_Medium_65_Medium.ttf").getFile(), "UTF-8");
        PDType0Font font = PDType0Font.load(pdDocument, new File(fontFile));

        // 设置透明度状态对象
        PDExtendedGraphicsState graphicsState = new PDExtendedGraphicsState();
        graphicsState.setNonStrokingAlphaConstant(0.2f);
        graphicsState.setAlphaSourceFlag(true);
        graphicsState.setStrokingAlphaConstant(0.2f);

        //设置水印名
        String waterText = "江苏专转本网课报名vx：mmxchanglu";

        //遍历原先的pdf文档
        for (PDPage page : pdDocument.getPages()) {
            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();
            //添加水印   要求：旋转45°，不透明度30%
            float waterTextWidth = font.getStringWidth(waterText) / 1000 * 30;
            float waterCenteredX = (pageWidth - waterTextWidth) / 2;
            float waterCenteredY = pageHeight / 2;
            //创建一个水印内容流
            PDPageContentStream waterContentStream = new PDPageContentStream(pdDocument, page, PDPageContentStream.AppendMode.APPEND, true, true);
            waterContentStream.beginText();
            waterContentStream.setFont(font, 30);
            // 设置不透明度
            waterContentStream.setNonStrokingColor(0, 0, 0); // black color
            waterContentStream.setStrokingColor(0, 0, 0); // black color
            waterContentStream.setGraphicsStateParameters(graphicsState);//设置透明度
            //设置旋转文本 45° 对于tx、ty是以左下角为偏移位置中心来进行旋转角度
            waterContentStream.setTextRotation(Math.toRadians(45), 400, -50);
            //设置文本
            waterContentStream.newLineAtOffset(waterCenteredX, waterCenteredY);
            waterContentStream.showText(waterText);
            waterContentStream.endText();
            waterContentStream.close();
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
