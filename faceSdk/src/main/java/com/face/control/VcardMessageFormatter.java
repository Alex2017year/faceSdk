package com.face.control;

import com.face.model.VcardMessage;
import com.face.utils.BufferUtil;
import com.face.utils.CRC16Util;
import com.face.utils.Encryption;

import static java.lang.System.arraycopy;

public class VcardMessageFormatter implements IMessageFormatter {

    private static final int FIXED_LEN = 14; // 除了数据码部分为未知长度
    private static final int DEVICE_DATA_LEN = 8; // 设备编号 + 数据长度
    private static final int FIXED_ENCRYPTION_LEN = 6; // 加密区除了data之外的总长度
    private static final short MAX_CMD_INDEX = 0x7FFF; // 最大的命令序列号，超过此数值就需要从0开始。
    private static final String KEY = "&AccessDoor@2016"; // 客户端通信密钥
    private byte[] sessionIvBytes = new byte[16]; // 加密向量
    private boolean bInit = false; // 判断加密向量是否被初始化

    public VcardMessageFormatter() {
        setSessionIvBytes(KEY.getBytes());
    }

    public VcardMessageFormatter(byte[] ivBytes) {
        setSessionIvBytes(ivBytes);
    }

    // 设置加密向量
    public void setSessionIvBytes(byte[] iv) {
        if (iv == null || iv.length != 16) return;
        arraycopy(iv, 0, sessionIvBytes, 0, sessionIvBytes.length);
        bInit = true;
    }

    private int calcPacketLength(int dataSize) {
        int packetLength;
        if ((dataSize + FIXED_ENCRYPTION_LEN) % 16 == 0) {
            packetLength = dataSize + FIXED_ENCRYPTION_LEN + 16;
        } else {
            packetLength = ((dataSize+FIXED_ENCRYPTION_LEN)/16 + 1)*16;
        }
        packetLength += DEVICE_DATA_LEN; //设备编码 + 数据长度
        return packetLength;
    }

    @Override
    public boolean msgToBuffer(VcardMessage msg, byte[] buffer) {
        if (!bInit) return false;

        // 计算数据包长度
        if (msg.getData() == null)
            msg.setLength(calcPacketLength(0));
        else
            msg.setLength((calcPacketLength(msg.getData().length)));

        buffer = new byte[msg.getLength()];
        BufferUtil.putInt(buffer, msg.getDeviceId(), 0); // 4
        BufferUtil.putInt(buffer, msg.getLength(), 4); // 4+4
        BufferUtil.putShort(buffer, msg.getInfoCode(), 8); // 4+4+2
        BufferUtil.putByte(buffer, msg.getCategoryCode(), 10); // 4+4+2+1
        BufferUtil.putByte(buffer, msg.getCommandCode(), 11); // 4+4+2+1+1
        BufferUtil.append(buffer, 12, msg.getData()); // 4+4+2+1+1+data.length

        // 计算crc16
        short crc16 = (short) CRC16Util.calcCRC16(buffer, 0, FIXED_LEN + msg.getDataSize());
        msg.setCrc16Code(crc16);
        BufferUtil.putShort(buffer, msg.getCrc16Code(), FIXED_LEN + msg.getDataSize());

        // 开始加密数据
        byte[] encryData = BufferUtil.extractBytes(buffer, DEVICE_DATA_LEN, FIXED_ENCRYPTION_LEN + msg.getDataSize());
        encryData = new Encryption().encrypt(encryData, KEY.getBytes(), sessionIvBytes); // 使用加密密钥作为加密向量
        if (encryData == null) return false;
        if (encryData.length != msg.getLength() - DEVICE_DATA_LEN) return false;

        arraycopy(encryData, 0, buffer, DEVICE_DATA_LEN, encryData.length); // 将加密数据拷贝到buffer
        return true;
    }

    @Override
    public boolean bufferToMsg(byte[] buffer, VcardMessage msg) {
        if (!bInit) return false;
        if (buffer == null) return false;

        // 开始解析数据
        int deviceId = BufferUtil.extractInt(buffer, 0);
        if (deviceId == -1) return false;
        msg = new VcardMessage(false);
        msg.setDeviceId(deviceId);

        int len = BufferUtil.extractInt(buffer, 4);
        if (len == -1 || len != buffer.length || len < FIXED_LEN) return false;

        // 开始进行解密
        byte[] encryptionBytes = new byte[buffer.length - DEVICE_DATA_LEN];
        arraycopy(buffer, DEVICE_DATA_LEN, encryptionBytes, 0, encryptionBytes.length);
        byte[] decryptBytes = new Encryption().decrypt(encryptionBytes, KEY.getBytes(), sessionIvBytes);

        short infoCode = BufferUtil.extractShort(decryptBytes, 0);
        if (infoCode == -1) return false;
        msg.setInfoCode(infoCode);

        byte categoryCode = BufferUtil.extractByte(decryptBytes, 2);
        if (categoryCode == -1) return false;
        msg.setCategoryCode(categoryCode);

        byte commandCode = BufferUtil.extractByte(decryptBytes, 3);
        if (commandCode == -1) return false;
        msg.setCommandCode(commandCode);

        byte[] data = BufferUtil.extractBytes(decryptBytes, 4, buffer.length - FIXED_LEN);
        msg.setData(data);

        short crc16 = BufferUtil.extractShort(decryptBytes, buffer.length-2);
        if (crc16 == -1) return false;
        msg.setCrc16Code(crc16);

        // FIXME: 需要在这里补上服务端的数据的crc16是否正确

        return true;
    }
}
