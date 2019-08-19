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
    public final static int DEVICES_PARAMETER_CATEGORY = 0x01;
    public final static int DEVICES_CONTROL_CATEGORY = 0x02;
    public final static int DEVICES_AUTHORIZATION_CATEGORY = 0x03;
    public final static int ACCESS_RECORD_EVENT_CATEGORY = 0x04;
    public final static int AUTHORIZED_FACE_RECOGNITION_CATEGORY = 0x05;
    public final static int COMPOSITE_DATA_CATEGORY = 0x06;
    public final static int FILE_UPLOAD_CATEGORY = 0x07;
    public final static int AUTHORIZATION_CODE_CATEGORY = 0x08;
    public final static int UI_SET_CATEGORY = 0x09;

    // 控制码中：命令
    public final static int COMMAND_01 = 0x00;
    public final static int COMMAND_02 = 0x02;
    public final static int COMMAND_03 = 0x03;
    public final static int COMMAND_04 = 0x04;
    public final static int COMMAND_05 = 0x05;
    public final static int COMMAND_06 = 0x06;
    public final static int COMMAND_07 = 0x07;
    public final static int COMMAND_08 = 0x08;
    public final static int COMMAND_09 = 0x09;
    public final static int COMMAND_0A = 0x0A;
    public final static int COMMAND_0B = 0x0B;
    public final static int COMMAND_0C = 0x0C;
    public final static int COMMAND_0D = 0x0D;
    public final static int COMMAND_0E = 0x0E;
    public final static int COMMAND_0F = 0x0F;
    public final static int COMMAND_10 = 0x10;

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
