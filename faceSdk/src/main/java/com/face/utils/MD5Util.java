package com.face.utils;
import java.security.MessageDigest;

public class MD5Util {
    public static String md5(String hexStr) {
        String result = "";

        try {
            byte[] encryptStr = md5(hexStr.getBytes());
            if (encryptStr == null) return result;

            int temp;
            StringBuffer sb=new StringBuffer("");
            for ( int offset = 0; offset <encryptStr.length ; offset++ ) {
                temp=encryptStr[offset];
                if(temp<0) temp+=256;
                if(temp<16) sb.append("0");
                sb.append(Integer.toHexString(temp));
            }
            result = sb.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public static byte[] md5(byte[] bytes) {
        byte[] result = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            result = md.digest();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}

