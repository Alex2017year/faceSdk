package com.face.model;

import com.face.utils.BufferUtil;
import com.face.utils.MD5Util;

public class ServerRegisterInfo {
    private static final String OEMCODE_SERVER = "xk1FFriW";
    private static final int VALID_SIZE = 16;
    private static final byte START_INDEX = 4;
    private static final byte LENGTH = 8;
    public short deviceNo;
    public int timestamp;
    public short randomNum;
    public long signature;

    public boolean transformFromBytes(byte[] sources) {
        if (sources == null) return false;
        if (sources.length != VALID_SIZE) return false;

        deviceNo = BufferUtil.extractShort(sources, 0);
        timestamp = BufferUtil.extractInt(sources, 2);
        randomNum = BufferUtil.extractShort(sources, 6);
        signature = BufferUtil.extractLong(sources, 8);

        return true;
    }

    public boolean isValidMsg() {
        byte[] bytes = new byte[8];
        BufferUtil.putShort(bytes, deviceNo, 0); // 2
        BufferUtil.putInt(bytes, timestamp, 2); // 2+4
        BufferUtil.putShort(bytes, randomNum, 6); // 2+4+2
        BufferUtil.putString(bytes, OEMCODE_SERVER, 8);

        bytes = BufferUtil.getPartBytes(MD5Util.md5(bytes), START_INDEX, LENGTH);

        return signature == BufferUtil.extractLong(bytes, 0);
    }
}
