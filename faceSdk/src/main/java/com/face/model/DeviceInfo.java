package com.face.model;

import com.face.utils.BufferUtil;
import com.face.utils.MD5Util;
import java.util.Random;

public class DeviceInfo {
    private static final String OEMCODE_DEVICE = "ShAvx9zt";
    private static final byte VERSION = (byte)1;
    private static final byte START_INDEX = 4;
    private static final byte LENGTH = 8;
    public int deviceNo;
    public byte registerReason;
    public byte version = VERSION;
    public int deviceTime;
    public short randomNum = 0;
    public byte[] signature;

    public static short genRandom() {
        return Integer.valueOf(new Random().nextInt()).shortValue();
    }

    public static byte[] genSignature(short randomNum, int deviceNo, byte version, int deviceTime) {

        byte[] result = new byte[30];

        BufferUtil.putShort(result, randomNum, 0); // 2
        BufferUtil.putInt(result, deviceNo, 2); // 2+4
        BufferUtil.putByte(result, version, 6); // 2+4+1
        BufferUtil.putInt(result, deviceTime, 7); // 2+4+1+4
        BufferUtil.putString(result, OEMCODE_DEVICE, 11);
        result = BufferUtil.getPartBytes(MD5Util.md5(result), START_INDEX, LENGTH);
        return result;
    }

    public byte[] toBytes() {
        if (signature == null) return null;

        byte[] result = null;
        byte[] intBytes1 = BufferUtil.int2Bytes(deviceNo);
        byte[] bytes1 = BufferUtil.byte2Bytes(registerReason);
        byte[] bytes2 = BufferUtil.byte2Bytes(version);
        byte[] intBytes2 = BufferUtil.int2Bytes(deviceTime);
        byte[] shortBytes = BufferUtil.short2Bytes(randomNum);

        result = BufferUtil.concat(intBytes1, bytes1, bytes2, intBytes2, shortBytes, signature);
        return result;
    }
}
