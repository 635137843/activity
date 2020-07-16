package com.activity.demo.strategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * @program: eoms-workflow
 * @description: 策略模式客户端
 * @author: HeMingXin
 * @create: 2020/7/16 19:51
 **/
public class Main {
    public static void main(String[] args) throws IOException {
        Path inFile = new File("D:\\sql1.txt").toPath();
        File outFile = new File("D:\\sql2.txt");
        Compressor gzipCompressor = new Compressor(GZIPOutputStream::new);
        gzipCompressor.compress(inFile, outFile);
        Compressor zipCompressor = new Compressor(ZipOutputStream::new);
        zipCompressor.compress(inFile, outFile);
    }
}
