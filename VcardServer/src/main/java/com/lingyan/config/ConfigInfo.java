package com.lingyan.config;

public class ConfigInfo {

    public static String KEY = "&AccessDoor@2016"; // 客户端通信密钥
    public static int IVS_COUNT = 16; // 加密/解密向量指定,
    private static byte[] IVS = null; // 设备端注册接口通信包最后16个字节作为注册接口通信包

    public static byte[] getIVS() {
        return IVS;
    }
    public static void setIVS(byte[] ivs) {
        ConfigInfo.IVS = IVS;
    }

    public static String getCommKey() {
        return KEY;
    }
}
