package com.changlu.demos;

import com.changlu.Main;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Description: Apache PDFBox案例：添加页眉、页脚
 * @Author: changlu
 * @Date: 1:38 PM
 */
public class Demo3 {
    public static void main(String[] args) throws Exception{
        //读取resources目录下input.pdf文件
        InputStream is = Main.class.getClassLoader().getResourceAsStream("input.pdf");
        PDDocument pdDocument = PDDocument.load(is);

        //自定义字体 C:\Users\93997\Desktop\watermark tools\watermarkTools\target\classes\ttfs
        //URLDecoder.decode() 方法来解码 URL 编码的路径，将 %20 转换回空格
        String fontFile = URLDecoder.decode(Main.class.getClassLoader().getResource(File.separator + "ttfs" + File.separator + "Alibaba_PuHuiTi_2.0_65_Medium_65_Medium.ttf").getFile(), "UTF-8");
        PDType0Font font = PDType0Font.load(pdDocument, new File(fontFile));
        float fontSize = 10; // 设置字体大小为12

        // 设置透明度状态对象
        PDExtendedGraphicsState graphicsState = new PDExtendedGraphicsState();
        graphicsState.setNonStrokingAlphaConstant(0.2f);
        graphicsState.setAlphaSourceFlag(true);
        graphicsState.setStrokingAlphaConstant(0.2f);

        //设置新的页眉
        String headerText = "咨询专转本默默学课程联系官方报名处QQ：3503851091，更多资料可加群828303961";
        String footerText = "江苏专转本公众号：专转本智慧树";

        //遍历原先的pdf文档
        for (PDPage page : pdDocument.getPages()) {
            float pageWidth = page.getMediaBox().getWidth();
            //计算页眉的居中位置
            float headerTextWidth = font.getStringWidth(headerText) / 1000 * fontSize;
            float headerCenteredX = (pageWidth - headerTextWidth) / 2; // 计算水平居中位置
            //计算页脚的居中位置
            float footerTextWidth = font.getStringWidth(footerText) / 1000 * fontSize;
            float footerCenteredX = (pageWidth - footerTextWidth) / 2; // 计算水平居中位置

            // 创建用于页眉的内容流
            PDPageContentStream headerContentStream = new PDPageContentStream(pdDocument, page, PDPageContentStream.AppendMode.APPEND, true, true);
            headerContentStream.beginText(); // 开始文本操作
            headerContentStream.setFont(font, fontSize); // 设置字体和字号
            headerContentStream.newLineAtOffset(headerCenteredX, page.getMediaBox().getHeight() - 30); // 设置文本起始位置
            headerContentStream.showText(headerText); // 绘制页眉内容
            headerContentStream.endText(); // 结束文本操作
            headerContentStream.close(); // 关闭内容流

            // 添加页脚
            PDPageContentStream footerContentStream = new PDPageContentStream(pdDocument, page, PDPageContentStream.AppendMode.APPEND, true, true);
            footerContentStream.beginText(); // 开始文本操作
            footerContentStream.setFont(font, fontSize); // 设置字体和字号
            footerContentStream.newLineAtOffset(footerCenteredX, 30); // 设置文本起始位置
            footerContentStream.showText(footerText); // 绘制页脚内容
            footerContentStream.endText(); // 结束文本操作
            footerContentStream.close(); // 关闭内容流
        }

        //目标目录 Thread.currentThread().getContextClassLoader().getResource("").getPath()  当前工程目录路径
        //String targetPDFPath = URLDecoder.decode(Demo3.class.getClassLoader().getResource("resources").getPath() + File.separator + "output.pdf", "UTF-8");
//        String targetPDFPath = URLDecoder.decode(Main.class.getClassLoader().getResource("output.pdf").getFile(), "UTF-8");
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
