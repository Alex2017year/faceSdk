package com.face.model;

import com.face.utils.BufferUtil;
import com.face.utils.CRC16Util;
import com.face.utils.Encryption;

import static java.lang.System.arraycopy;

/**
 * send and receive messages
 * underlying protocol
 */
public class VcardMessage {
    public VcardMessage(boolean bClientData) {
        this.bClientData = bClientData;
    }

    public VcardMessage(boolean bClientData, int deviceId) {
        this.deviceId = deviceId;
        this.bClientData = bClientData;
    }

    public VcardMessage(boolean bClientData, int deviceId, short infoCode, byte category, byte command, byte[] data) {
        this.bClientData = bClientData;
        this.infoCode = infoCode;
        this.deviceId = deviceId;
        this.categoryCode = category;
        this.commandCode = command;
        this.data = data;
    }

    public void setCmdIndex(short cmdIndex) {
        this.cmdIndex = cmdIndex;
        if (this.bClientData) {
            this.infoCode = (short)((short) 0x8000 | cmdIndex); // 最高位变为1
        } else {
            this.infoCode = cmdIndex;
        }
    }

    // 返回当前的命令index
    public short getCmdIndex() {
        return this.cmdIndex;
    }
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public void setInfoCode(short infoCode) {
        this.infoCode = infoCode;
    }

    public void setCategoryCode(byte categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setCommandCode(byte commandCode) {
        this.commandCode = commandCode;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    private int deviceId; // 数据编号
    private int length; // 加密后整包长度，内部计算
    private short infoCode; // 信息代码
    private short crc16Code; // 校验码，内部计算
    private byte categoryCode; // 控制码 - 分类
    private byte commandCode; // 控制码 - 命令
    private byte[] data; // 数据码 - 数据
    private short cmdIndex; // 命令发送的序列号
    private boolean bClientData; // 表明是否为客户端发送的数据

    private static final int FIXED_LEN = 14; // 除了数据码部分为未知长度
    private static final int DEVICE_DATA_LEN = 8; // 设备编号 + 数据长度
    private static final int FIXED_ENCRYPTION_LEN = 6; // 加密区除了data之外的总长度
    private static final short MAX_CMD_INDEX = 0x7FFF; // 最大的命令序列号，超过此数值就需要从0开始。


    // 加密
    private byte[] encoder() {

        return null;
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

    // 将对象属性元素打包成bytes
    // 加密打包数据
    public byte[] transformToBytes() {
        if (data == null) return null;
        this.length = calcPacketLength(data.length);
        byte[] destBytes = new byte[this.length];

        BufferUtil.putInt(destBytes, this.deviceId, 0); // 4
        BufferUtil.putInt(destBytes, this.length, 4); // 4+4
        BufferUtil.putShort(destBytes, this.infoCode, 8); // 4+4+2
        BufferUtil.putByte(destBytes, this.categoryCode, 10); // 4+4+2+1
        BufferUtil.putByte(destBytes, this.commandCode, 11); // 4+4+2+1+1
        BufferUtil.append(destBytes, 12, this.data); // 4+4+2+1+1+data.length
        this.crc16Code = (short)CRC16Util.calcCRC16(destBytes, 0, FIXED_LEN+data.length);
        BufferUtil.putShort(destBytes, this.crc16Code, FIXED_LEN+data.length);

        // 开始加密数据
        byte[] encryData = BufferUtil.extractBytes(destBytes, DEVICE_DATA_LEN, FIXED_ENCRYPTION_LEN+data.length);
        byte[] keyBytes = null; // 此处需要给一个密钥，否则就很难加密
        encryData = new Encryption().encrypt(encryData, keyBytes);
        if (encryData == null) return null; // 加密错误

        if (encryData.length != this.length - DEVICE_DATA_LEN) return null; // 很容易出错
        arraycopy(destBytes, DEVICE_DATA_LEN, encryData, 0, encryData.length); // 将加密数据拷贝到dest
        return destBytes;
    }

    // 解密 + 还原数据
    public VcardMessage transformFromBytes(byte[] sourceData) {
        if (sourceData == null) return null;

        return null;
    }

    // 解密
    private byte[] decoder() {
        return null;
    }

    public static class ErrCode {
        public final static int OK = Constants.OK;
        public final static int CHECK_ERR = Constants.CHECK_ERR;
        public final static int PARAMETER_ERR = Constants.PARAMETER_ERR;
        public final static int BUFFER_OVERFLOW_ERR = Constants.BUFFER_OVERFLOW_ERR;
        public final static int REINVENT_ERR = Constants.REINVENT_ERR;
        public final static int CONFIG_OR_HARDWARE_ERR = Constants.CONFIG_OR_HARDWARE_ERR;
    }
}
