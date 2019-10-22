package com.lingyan.global;

public class Constant {

    // 设备通信状态
    public enum ConnectionStatus {
        COM_FAILED((byte) 0),
        HEALTHY((byte) 1),
        Registration_Request((byte) 2);

        public byte value;
        ConnectionStatus(byte value) {
            this.value = value;
        }
    }
}
