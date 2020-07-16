package com.activity.demo.strategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Description  具体策略
 * @Author  HeMingXin
 * @Date  2020/7/16 20:05
 */
public class ZipCompressionStrategy implements CompressionStrategy {
    @Override
    public OutputStream compress(OutputStream data) throws IOException {
        return new ZipOutputStream(data);
    }
}