package com.face.model;

/**
 * send and receive messages
 * underlying protocol
 */
public class VcardMessage {
    private int deviceId; // 数据编号
    private int length; // 加密后整包长度，内部计算
    private short infoCode; // 信息代码
    private short crc16Code; // 校验码，内部计算
    private byte categoryCode; // 控制码 - 分类
    private byte commandCode; // 控制码 - 命令
    private byte[] data; // 数据码 - 数据
    private short cmdIndex; // 命令发送的序列号
    private boolean bClientData; // 表明是否为客户端发送的数据

    // 可以用作接收数据的构造方法
    public VcardMessage(boolean bClientData) {
        this.bClientData = bClientData;
    }

    // 可以用作发送数据的构造方法
    public VcardMessage(boolean bClientData, int deviceId) {
        this.deviceId = deviceId;
        this.bClientData = bClientData;
    }

    // 用作发送数据的构造方法
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
    public short getCmdIndex() {
        if (!bClientData)
            this.cmdIndex = this.infoCode;
        else
            this.cmdIndex = (short)(this.infoCode | 0x7FFF); // 去除最高位
        return this.cmdIndex;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
    public int getDeviceId() {
        return this.deviceId;
    }

    public void setInfoCode(short infoCode) {
        this.infoCode = infoCode;
    }
    public short getInfoCode() {
        return this.infoCode;
    }

    public void setCategoryCode(byte categoryCode) {
        this.categoryCode = categoryCode;
    }
    public byte getCategoryCode() {
        return this.categoryCode;
    }

    public void setCommandCode(byte commandCode) {
        this.commandCode = commandCode;
    }
    public byte getCommandCode() {
        return this.commandCode;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    public byte[] getData() {
        return data;
    }

    public int getDataSize() {
        if (data == null) return 0;
        return data.length;
    }

    public void setLength(int len) {
        this.length = len;
    }
    public int getLength() {
        return this.length;
    }

    public void setCrc16Code(short crc16Code) {
        this.crc16Code = crc16Code;
    }
    public short getCrc16Code() {
        return this.crc16Code;
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
