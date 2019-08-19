package com.face.model;

import com.face.utils.MD5Util;

import java.text.SimpleDateFormat;
import java.util.Random;

public class DeviceInfo {
    private static final String OEMCode_Device = "ShAvx9zt";
    public int deviceNo;
    public byte registerReason;
    public byte version;
    public int deviceTime;
    public short randomNum = 0;
    public byte[] signature;
    private static SimpleDateFormat sdf;


    private short genRandom() {
        randomNum = Integer.valueOf(new Random().nextInt()).shortValue();
        return randomNum;
    }

    public byte[] genSignature(short randomNum, int deviceNo, byte version, int deviceTime) {
        
        // MD5Util.md5()

        return null;
    }
}
