package com.activity.demo.strategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @Description  具体策略
 * @Author  HeMingXin
 * @Date  2020/7/16 20:05
 */
public class GzipCompressionStrategy implements CompressionStrategy {
    @Override
    public OutputStream compress(OutputStream data) throws IOException {
        return new GZIPOutputStream(data);
    }
}