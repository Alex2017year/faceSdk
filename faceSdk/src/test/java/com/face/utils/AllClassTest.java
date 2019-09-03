package com.face.utils;

import static org.junit.Assert.assertTrue;

import com.face.model.Constants;
import com.face.model.DeviceInfo;
import org.junit.Test;

import java.util.Arrays;

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

    @Test
    public void genSignatureTest() {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.deviceNo = 0;
        deviceInfo.deviceTime = (int)(System.currentTimeMillis() / 1000);
        deviceInfo.randomNum = DeviceInfo.genRandom();
        deviceInfo.registerReason = Constants.STARTUP_REG;
        deviceInfo.signature = DeviceInfo.genSignature(deviceInfo.randomNum, deviceInfo.deviceNo, deviceInfo.version, deviceInfo.deviceTime);

        System.out.println(deviceInfo.toBytes().length);
        System.out.println(Arrays.toString(deviceInfo.toBytes()));
        assertTrue(deviceInfo.toBytes().length == 20);
    }




}
