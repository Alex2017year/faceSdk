package com.face.utils;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AllClassTest {

    @Test
    public void encryptionAndDecrypt() {
        Encryption encryption = new Encryption();
        byte[] keyBytes = { 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38 };
        String content = "9865";
        byte[] encryptResult = encryption.encrypt(content.getBytes(), keyBytes);
        byte[] decryptResult = encryption.decrypt(encryptResult, keyBytes);

        assertTrue(new String(decryptResult).equals(content));
    }


    @Test
    public void crc16Test() {
        String str = "123456789";
        int result = CRC16Util.calcCRC16(str.getBytes());
        System.out.println("result: "+result);

        assertTrue(result != 0);
    }

    @Test
    public void long2Bytes() {
        long num = 16l;
        byte[] result = BufferUtil.long2Bytes(num);
        try {
            System.out.println(new String(result, "UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        assertTrue(result.length == 8);

    }
}
