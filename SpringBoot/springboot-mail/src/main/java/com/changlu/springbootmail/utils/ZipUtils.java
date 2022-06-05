package com.changlu.springbootmail.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

public class ZipUtils {

    private static final Logger logger = LoggerFactory.getLogger(ZipUtils.class);

    private static String folder = "C:\\Users\\93997\\Desktop\\upload";
    private static String zipFile = "C:\\Users\\93997\\Desktop\\static.zip";


    public static void main(String[] args) throws IOException {
//        System.out.println(packet(Paths.get(folder), Paths.get(zipFile)));
        System.out.println(Paths.get(ZipUtils.folder).toString());
    }

    /**
     * 压缩指定的目录
     * @param folder
     * @param zipFile
     * @throws IOException
     */
    public synchronized static String packet(Path folder, Path zipFile) throws IOException {
        if (!Files.isDirectory(folder)) {
            throw new IllegalArgumentException(folder.toString() + " 不是合法的文件夹");
        }
        //若是该压缩包已经存在，那么进行删除后再压缩
        synchronized (ZipUtils.class) {
            if (zipFile.toFile().exists()) {
                zipFile.toFile().delete();
            }
        }
        OutputStream outputStream = Files.newOutputStream(zipFile, StandardOpenOption.CREATE_NEW);
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        LinkedList<String> path = new LinkedList<>();

        try {
            Files.walkFileTree(folder, new FileVisitor<Path>() {

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

                    if (!dir.equals(folder)) {
                        // 开始遍历目录
                        String folder = dir.getFileName().toString();
                        path.addLast(folder);
                        // 写入目录
                        ZipEntry zipEntry = new ZipEntry(path.stream().collect(Collectors.joining("/", "", "/")));
                        try {
                            zipOutputStream.putNextEntry(zipEntry);
                            zipOutputStream.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    // 开始遍历文件
                    try (InputStream inputStream = Files.newInputStream(file)) {

                        // 创建一个压缩项，指定名称
                        String fileName = path.size() > 0
                                ? path.stream().collect(Collectors.joining("/", "", "")) + "/" + file.getFileName().toString()
                                : file.getFileName().toString();

                        ZipEntry zipEntry = new ZipEntry(fileName);

                        // 添加到压缩流
                        zipOutputStream.putNextEntry(zipEntry);
                        // 写入数据
                        int len = 0;
                        byte[] buffer = new byte[1024 * 10];
                        while ((len = inputStream.read(buffer)) > 0) {
                            zipOutputStream.write(buffer, 0, len);
                        }

                        zipOutputStream.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    // 结束遍历目录
                    if (!path.isEmpty()) {
                        path.removeLast();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            zipOutputStream.closeEntry();
            logger.info("图片压缩成功，打包路径为：" + zipFile.toString());
            return zipFile.toString();
        } finally {
            zipOutputStream.close();
            outputStream.close();
        }
    }
}
