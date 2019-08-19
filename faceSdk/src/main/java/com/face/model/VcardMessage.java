package com.face.model;

/**
 * send and receive messages
 */
public class VcardMessage {

    public VcardMessage() { }

    public VcardMessage(int deviceId) {
        this.deviceId = deviceId;
    }

    public VcardMessage(int deviceId, char infoCode,byte category, byte command, byte[] data) {
        this.infoCode = infoCode;
        this.deviceId = deviceId;
        this.categoryCode = category;
        this.commandCode = command;
        this.data = data;
    }

    public int deviceId;
    public int length;
    public char infoCode;
    public short crc16Code;
    public byte categoryCode;
    public byte commandCode;
    public byte[] data;
    private long cmdIndex;

    // 加密
    private byte[] encoder() {
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
