package com.face.utils;

import static java.lang.System.arraycopy;
import static org.junit.Assert.assertTrue;

import com.face.model.Constants;
import com.face.model.DeviceInfo;
import com.face.model.VcardMessage;
import org.junit.Test;

import java.util.Arrays;

public class AllClassTest {

    @Test
    public void encryptionAndDecrypt() {
        Encryption encryption = new Encryption();
        byte[] keyBytes = {0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38};
        String content = "9865";
        byte[] encryptResult = encryption.encrypt(content.getBytes(), keyBytes);
        byte[] decryptResult = encryption.decrypt(encryptResult, keyBytes);

        assertTrue(new String(decryptResult).equals(content));
    }

    @Test
    public void crc16Test() {
        String str = "123456789";
        int result = CRC16Util.calcCRC16(str.getBytes());
        System.out.println("result: " + result);

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
        deviceInfo.deviceTime = (int) (System.currentTimeMillis() / 1000);
        deviceInfo.randomNum = DeviceInfo.genRandom();
        deviceInfo.registerReason = Constants.STARTUP_REG;
        deviceInfo.signature = DeviceInfo.genSignature(deviceInfo.randomNum, deviceInfo.deviceNo, deviceInfo.version, deviceInfo.deviceTime);

        System.out.println(deviceInfo.toBytes().length);
        System.out.println(Arrays.toString(deviceInfo.toBytes()));
        assertTrue(deviceInfo.toBytes().length == 20);
    }

    @Test
    public void transformToBytesTest() {
        byte[] data = new byte[]{(byte) 19, (byte) 199, (byte) 25, (byte) 96, (byte) 85, (byte) 63};
        VcardMessage vm = new VcardMessage(0, (short) 188, (byte) 0xFF, (byte) 0, data); // int deviceId, short infoCode, byte category, byte command, byte[] data

        byte[] result = vm.transformToBytes();
        System.out.println(Arrays.toString(result));
        assertTrue(true);
    }

    @Test
    public void testArraycopy() {
        byte[] src = new byte[]{(byte) 14, (byte) 20, (byte) 1, (byte) 3, (byte) 5};
        byte[] dest = new byte[2];
        arraycopy(src, 2, dest, 0, 2);
        System.out.println(Arrays.toString(dest));
        assertTrue(true);
    }


}
