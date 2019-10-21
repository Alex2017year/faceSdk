package com.lingyan.config;

public class ConfigInfo {

    public static String KEY = "&AccessDoor@2016"; // 客户端通信密钥
    public static int IVS_COUNT = 16; // 加密/解密向量指定

    public static String getCommKey() {
        return KEY;
    }
}
