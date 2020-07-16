package com.activity.demo.strategy;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description 策略接口
 * @Author  HeMingXin
 * @Date  2020/7/16 20:04
 */
public interface CompressionStrategy {
    OutputStream compress(OutputStream data) throws IOException;
}