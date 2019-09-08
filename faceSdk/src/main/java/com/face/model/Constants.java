package com.face.model;

public class Constants {
    // 错误码信息
    public final static int OK = 0x00;
    public final static int CHECK_ERR = 0x01;
    public final static int PARAMETER_ERR = 0x02;
    public final static int BUFFER_OVERFLOW_ERR = 0x03;
    public final static int REINVENT_ERR = 0x04;
    public final static int CONFIG_OR_HARDWARE_ERR = 0xFF;

    // 控制码中：分类
    public final static byte DEVICES_PARAMETER_CATEGORY = (byte) 0x01;
    public final static byte DEVICES_CONTROL_CATEGORY = (byte) 0x02;
    public final static byte DEVICES_AUTHORIZATION_CATEGORY = (byte) 0x03;
    public final static byte ACCESS_RECORD_EVENT_CATEGORY = (byte) 0x04;
    public final static byte AUTHORIZED_FACE_RECOGNITION_CATEGORY = (byte) 0x05;
    public final static byte COMPOSITE_DATA_CATEGORY = (byte) 0x06;
    public final static byte FILE_UPLOAD_CATEGORY = (byte) 0x07;
    public final static byte AUTHORIZATION_CODE_CATEGORY = (byte) 0x08;
    public final static byte UI_SET_CATEGORY = (byte) 0x09;
    public final static byte UNIVERSAL_CATEGORY = (byte) 0xFF; // 通用接口分类

    // 控制码中：命令
    public final static byte COMMAND_01 = (byte) 0x00;
    public final static byte COMMAND_02 = (byte) 0x02;
    public final static byte COMMAND_03 = (byte) 0x03;
    public final static byte COMMAND_04 = (byte) 0x04;
    public final static byte COMMAND_05 = (byte) 0x05;
    public final static byte COMMAND_06 = (byte) 0x06;
    public final static byte COMMAND_07 = (byte) 0x07;
    public final static byte COMMAND_08 = (byte) 0x08;
    public final static byte COMMAND_09 = (byte) 0x09;
    public final static byte COMMAND_0A = (byte) 0x0A;
    public final static byte COMMAND_0B = (byte) 0x0B;
    public final static byte COMMAND_0C = (byte) 0x0C;
    public final static byte COMMAND_0D = (byte) 0x0D;
    public final static byte COMMAND_0E = (byte) 0x0E;
    public final static byte COMMAND_0F = (byte) 0x0F;
    public final static byte COMMAND_10 = (byte) 0x10;

    // 注册原因
    public final static short STARTUP_REG = 0x00;
    public final static short TIMER_REG = 0x01;
    public final static short PASSWORD_FAILURE_REG = 0x02;
    public final static short AUTHENTICATION_FAILURE_REG = 0x03;
    public final static short AUTHENTICATION_FAILURE_ = 0x04;
    public final static short NEGOTIATE_FAILURE_ = 0x05;
    public final static short TIMEOUT_NET = 0x06;
    public final static short NET_ANOMALY = 0x07;
    public final static short CHECK_FAILURE = 0x08;
    public final static short SHORT_DATA = 0x09;

}
