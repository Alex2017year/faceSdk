package com.lingyan.bean;

public class DeviceObject {
    private long deviceId;
    private RegisterReason registerReason;
    private String programVerion;
    private short flashMemory;
    private String deviceAlias;
    private short agentCode;
    private int runningTime;
    private int localTime;
    private byte systemLoad;
    private short totalMemory; // 内存总量
    private short availableMemory; // 可使用内存量
    private short sharedMemory; // 共享内存使用量
    private short cacheMemory; // 缓冲内存使用量
    private PowerEnum powerType; // 电源类型
    private byte power; // 电量百分比
    private byte temperature;
    private byte protocolVersion;
    private String secretKey; // 应用密钥
    private UploadCode uploadCode; // 功能编号
    private String url; // 上传地址

    public enum RegisterReason {
        STARTUP_REG((byte) 0x00),
        TIMER_REG((byte) 0x01),
        REGISTER_DECRYPTION_FAILURE_REG((byte) 0x02),
        REGISTER_AUTHENTICATION_FAILURE_REG((byte) 0x03),
        DECRYPTION_FAILURE_REG((byte) 0x04),
        NEGOTIATE_FAILURE_REG((byte) 0x05),
        TIMEOUT_NET((byte) 0x06),
        NET_ANOMALY((byte) 0x07),
        CHECK_FAILURE((byte) 0x08),
        SHORT_DATA((byte) 0x09);

        public byte value;
        private RegisterReason(byte value) {
            this.value = value;
        }
    }

    public enum PowerEnum {
        POWER_SOURCE((byte)0), // 电源取电
        UPS((byte)1); // UPS 供电

        public byte value;
        private PowerEnum(byte value) {
            this.value = value;
        }
    }

    public enum UploadCode {
        PASSAGE_SNAPSHOTS_ADDRESS((byte) 0),
        FACE_SNAPSHOTS_ADDRESS((byte) 1),
        BACKLIST_FACE_SNAPSHOTS_ADDRESS((byte) 2),
        RECORD_ADDRESS((byte) 3),
        DOORBELL_ADDRESS((byte) 4);

        public byte value;
        private UploadCode(byte value) {
            this.value = value;
        }
    }
}
