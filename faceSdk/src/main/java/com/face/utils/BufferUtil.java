package com.face.utils;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.nio.ByteBuffer;
import java.util.Calendar;

import static java.lang.System.arraycopy;

public class BufferUtil {
    public static byte[] long2Bytes(long srcNum) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(srcNum);
        return buffer.array();
    }

    public static byte[] int2Bytes(int srcNum) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(srcNum);
        return buffer.array();
    }

    public static byte[] short2Bytes(short srcNum) {
        ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
        buffer.putShort(srcNum);
        return buffer.array();
    }

    public static byte[] byte2Bytes(byte srcNum) {
        ByteBuffer buffer = ByteBuffer.allocate(Byte.BYTES);
        buffer.put(srcNum);
        return buffer.array();
    }

    public static byte[] string2Bytes(String str) {
        return str.getBytes();
    }

    public static void putByte(byte[] bys, byte b, int index) {
        bys[index] = b;
    }

    public static void putShort(byte[] b, short s, int index) {
        b[index + 1] = (byte) (s >> 8);
        b[index + 0] = (byte) (s >> 0);
    }

    public static short getShort(byte[] b, int index) {
        return (short) (((b[index + 1] << 8) | b[index + 0] & 0xff));
    }

    public static void putInt(byte[] b, int x, int index) {
        b[index + 3] = (byte) (x >> 24);
        b[index + 2] = (byte) (x >> 16);
        b[index + 1] = (byte) (x >> 8);
        b[index + 0] = (byte) (x >> 0);
    }

    public static int getInt(byte[] bb, int index) {
        return (int) ((((bb[index + 3] & 0xff) << 24)
                     | ((bb[index + 2] & 0xff) << 16)
                     | ((bb[index + 1] & 0xff) << 8)
                     | ((bb[index + 0] & 0xff) << 0)));
    }

    public static void putLong(byte[] bb, long x, int index) {
        bb[index + 7] = (byte) (x >> 56);
        bb[index + 6] = (byte) (x >> 48);
        bb[index + 5] = (byte) (x >> 40);
        bb[index + 4] = (byte) (x >> 32);
        bb[index + 3] = (byte) (x >> 24);
        bb[index + 2] = (byte) (x >> 16);
        bb[index + 1] = (byte) (x >> 8);
        bb[index + 0] = (byte) (x >> 0);
    }


    public static long getLong(byte[] bb, int index) {
        return ((((long) bb[index + 7] & 0xff) << 56)
                | (((long) bb[index + 6] & 0xff) << 48)
                | (((long) bb[index + 5] & 0xff) << 40)
                | (((long) bb[index + 4] & 0xff) << 32)
                | (((long) bb[index + 3] & 0xff) << 24)
                | (((long) bb[index + 2] & 0xff) << 16)
                | (((long) bb[index + 1] & 0xff) << 8)
                | (((long) bb[index + 0] & 0xff) << 0));
    }

    public static byte[] getPartBytes(byte[] src, int startIdx, int len) {
        if (len <= 0) return null;
        if (startIdx <= 0) return null;
        if (src.length < (startIdx + len)) return null;

        byte[] result = new byte[len];

        for (int i = 0; i < len; i++) {
            result[i] = src[i+startIdx];
        }

        return result;
    }

    public static byte[] getBytes(byte[] src, int startIdx, int endIdx) {
        return getPartBytes(src, startIdx, endIdx-startIdx);
    }


    public static boolean append(byte[] src, int offset, byte[] paddingBytes) {
        if (src == null || paddingBytes == null) return false;
        if (src.length - offset < paddingBytes.length) return false;
        arraycopy(src, offset, paddingBytes, 0, paddingBytes.length);
        return true;
    }

    public static byte[] concat(byte[]... arrays) {
        byte length = 0;
        for (byte[] array : arrays) {
            length += array.length;
        }
        byte[] result = new byte[length];
        byte pos = 0;
        for (byte[] array : arrays) {
            arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }

    public static byte[] extractBytes(byte[] src, int offset, int length) {
        if (src == null || length <= 0 ) return null;
        byte[] result = new byte[length];
        arraycopy(src, offset, result, 0, length);
        return result;
    }

    public static Calendar bytesToDateTime(byte[] bytes, int offset) {
        long timestamp = getLong(bytes, offset);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp);
        return c;
    }

    public static void putString(byte[] src, String str, int index) {
        byte[] strBytes = str.getBytes();
        arraycopy(src, index, strBytes, 0, strBytes.length);
    }

    public static short extractShort(byte[] src, int offset) {
        if (src == null) return -1;
        if (src.length < 2) return -1;

        short result = (short) ((src[offset] & 0xFF) | ((src[offset+1] & 0xFF)<<8));
        return result;
    }

    public static int extractInt(byte[] src, int offset) {
        if (src == null) return -1;
        if (src.length < 4) return -1;

        int result = ((src[offset] & 0xFF)
                   | ((src[offset+1] & 0xFF)<<8)
                   | ((src[offset+2] & 0xFF)<<16)
                   | ((src[offset+3] & 0xFF)<<24));

        return result;
    }

    public static long extractLong(byte[] src, int offset) {
        if (src == null) return -1;
        if (src.length < 8) return -1;
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(src, offset, 8);
        buffer.flip();//need flip

        return buffer.getLong();
    }


}
