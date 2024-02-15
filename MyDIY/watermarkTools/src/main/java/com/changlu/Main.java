package com.changlu;

import com.changlu.utils.WordUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: changlu
 * @Date: 11:19 AM
 */
public class Main {

    //索引名称：目标名称  如：电子：2023.9.26电子日日练及答案
    private static Map<String, String> subjectNamesMap = new HashMap<String, String>(){
        {
            // 获取当前日期
            LocalDate currentDate = LocalDate.now();
            // 创建日期格式器
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            String dateStr = currentDate.format(formatter);
            //模板字符串
            String formatStr = dateStr + "%s" + "日日练及答案.pdf";
            this.put("高数", String.format(formatStr, "高数"));
            this.put("计算机", String.format(formatStr, "计算机"));
            this.put("电子", String.format(formatStr, "电子"));
            this.put("机械", String.format(formatStr, "机械"));
            this.put("化工", String.format(formatStr, "化工"));
            this.put("财经", String.format(formatStr, "财经"));
            this.put("管理", String.format(formatStr, "管理"));
            this.put("土木", String.format(formatStr, "土木"));
            this.put("医护", String.format(formatStr, "医护"));
        }
    };

    //处理文件目录
    private static String directoryPath = "C:\\Users\\93997\\Desktop\\日日练";
    private static String docxDirectoryPath = "tmp";//docs临时存放目录
    private static String pdfDirectoryPath = "pdf";//最终输出处理过后的pdf目录

    public static void main(String[] args) {
        //任务1：给定一个目录，检索该目录下所有文件，筛选出所有的docx以及PDF文件。
        File directory = new File(directoryPath);
        //搜集所有的docx文档
        List<String> waitWorkDocs = new ArrayList<>();
        //搜集所有的pdf名称
        List<String> waitWorkPDFs = new ArrayList<>();
        //遍历目录下所有的文件
        for (File file : directory.listFiles()) {
            //筛选不是目录
            if (!file.isDirectory()) {
                if (file.getName().endsWith(".docx")) {
                    //添加docx的文件名
                    waitWorkDocs.add(file.getName());
                }else if (file.getName().endsWith(".pdf")) {
                    waitWorkPDFs.add(file.getName());
                }
            }
        }

        //任务2：将目录下docx文件转为pdf文件，并将docx文件统一剪切到tmp目录。
        System.out.println("开始执行word转pdf任务...");
        //临时存放docx文件的目录 tmp，若是不存在创建
        String targetTmpDirectory = directoryPath + File.separator + docxDirectoryPath;
        if (!Files.exists(Paths.get(targetTmpDirectory))) {
            new File(targetTmpDirectory).mkdirs();
        }
        //将所有的docx进行转换，word转pdf
        for (String docxName : waitWorkDocs) {
            String originPath = directoryPath + File.separator + docxName;
            String targetPath = originPath.replace(".docx", ".pdf");
            //docx转为pdf文件
            WordUtils.wordToPdf(originPath, targetPath);
            //将docx剪切到tmp文件
            String targetTmpPath = targetTmpDirectory + File.separator + docxName;
            try {
                Files.move(Paths.get(originPath), Paths.get(targetTmpPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //添加pdf名称到pdf集合中
            String pdfName = targetPath.substring(targetPath.lastIndexOf(File.separator) + 1);
            waitWorkPDFs.add(pdfName);
        }

        //任务3：对所有的pdf文件来进行最定义【水印+广告】处理，将所有水印处理过的pdf文件统一输出到pdf目录。
        System.out.println();
        System.out.println("开始处理pdf打水印、广告任务....");
        //临时存放docx文件的目录 tmp，若是不存在创建
        String targetPDFDirectory = directoryPath + File.separator + pdfDirectoryPath;
        if (!Files.exists(Paths.get(targetPDFDirectory))) {
            new File(targetPDFDirectory).mkdirs();
        }
        //遍历所有的pdf文件来去添加水印及其他任务并输出pdf
        for (String waitWorkPDFName : waitWorkPDFs) {
            String originPDFPath = directoryPath + File.separator + waitWorkPDFName;
            String targetPDFPath = targetPDFDirectory + File.separator + fileNameTransfer(waitWorkPDFName);
            //执行真正的水印+广告任务
            try {
                work(originPDFPath, targetPDFPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //根据当前文件名称来匹配相应的文件名称
    public static String fileNameTransfer(String originName) {
        for (Map.Entry<String, String> entry : subjectNamesMap.entrySet()) {
            String name = entry.getKey();
            //若是当前名称中包含有科目名称
            if (originName.contains(name)) {
                return entry.getValue();
            }
        }
        return "未知" + System.currentTimeMillis() + ".pdf";
    }

    /**
     * 实际水印处理任务
     */
    public static void work(String originPDFPath, String targetPDFPath) throws Exception{
        //读取resources目录下input.pdf文件
//        InputStream is = Main.class.getClassLoader().getResourceAsStream("input.pdf");
        InputStream is = new FileInputStream(originPDFPath);
        PDDocument pdDocument = PDDocument.load(is);

        //自定义字体 C:\Users\93997\Desktop\watermark tools\watermarkTools\target\classes\ttfs
        //URLDecoder.decode() 方法来解码 URL 编码的路径，将 %20 转换回空格
//        PDType0Font font = PDType0Font.load(pdDocument, new File("C:\\Users\\93997\\Desktop\\watermark tools\\watermarkTools\\src\\main\\resources\\ttfs\\Alibaba_PuHuiTi_2.0_65_Medium_65_Medium.ttf"));
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
        String waterText = "江苏专转本网课报名vx：mmxchanglu";

        //遍历原先的pdf文档
        for (PDPage page : pdDocument.getPages()) {
            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();
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

            //添加图片水印
            //创建一个水印内容流
            PDPageContentStream imageContentStream = new PDPageContentStream(pdDocument, page, PDPageContentStream.AppendMode.APPEND, true, true);
            // 创建图像对象
//            PDImageXObject image = PDImageXObject.createFromFile("C:\\Users\\93997\\Desktop\\watermark tools\\watermarkTools\\src\\main\\resources\\images\\ConsultationGroupQRCode.jpg", pdDocument);
            String pictureFile = URLDecoder.decode(Main.class.getClassLoader().getResource(File.separator + "images" + File.separator + "ConsultationGroupQRCode.jpg").getFile(), "UTF-8");
            PDImageXObject image = PDImageXObject.createFromFile(pictureFile, pdDocument);
            // 计算图像的宽度和高度（缩小比例为0.3）
            float imageWidth = (float) (image.getWidth() * 0.25);
            float imageHeight = (float) (image.getHeight() * 0.25);
            //具体图片位置
            float imageX = pageWidth - imageWidth - 10;
            float imageY = pageHeight - imageHeight - 10;

            // 在指定位置绘制图像
            imageContentStream.drawImage(image, imageX, imageY, imageWidth, imageHeight);
            imageContentStream.close();
        }

        //目标目录
//        File outputFile = new File("C:\\Users\\93997\\Desktop\\watermark tools\\watermarkTools\\src\\main\\resources\\output.pdf");
        File outputFile = new File(targetPDFPath);
        // 若是文件存在先进行删除
        Files.deleteIfExists(Paths.get(outputFile.toURI()));
        // 保存修改后的文档
        pdDocument.save(outputFile);
        System.out.println("转换任务：" + originPDFPath + "=>" + targetPDFPath + " 成功！");
        // 关闭文档
        pdDocument.close(); // 关闭文档
    }

}
