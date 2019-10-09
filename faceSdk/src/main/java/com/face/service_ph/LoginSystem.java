package com.face.service_ph;

import com.face.model.Constants;
import com.face.model.DeviceInfo;
import com.face.model.VcardMessage;
import com.face.utils.TimeUtil;

// 用于向服务器发起注册请求
// 做成单例模式
public class LoginSystem {

    private static LoginSystem instance = null;

    public static synchronized LoginSystem getInstance(){
        if(instance == null){
            instance = new LoginSystem();
        }
        return instance;
    }

    private LoginSystem() {
        mCommandMsg = new VcardMessage(true);
        mCommandMsg.setDeviceId(0);
        mCommandMsg.setCategoryCode(Constants.UNIVERSAL_CATEGORY);
        mCommandMsg.setCommandCode(Constants.COMMAND_01);
        mCommandMsg.setCmdIndex(cmdIndex);
        // mCommandMsg.setData(new byte[]{(byte) 0x14});

        DeviceInfo  mDeviceInfo = new DeviceInfo();
        mDeviceInfo.deviceNo = 0;
        mDeviceInfo.version = VERSION;
    }

    public void registerDevice() {
        mDeviceInfo.registerReason = Constants.STARTUP_REG; // FIXME: 这里需要重构！
        mDeviceInfo.randomNum = DeviceInfo.genRandom();
        mDeviceInfo.deviceTime = TimeUtil.getCurrentSec();
    }

    // 向外发送请求
    int sendCmd(VcardMessage msg) {
        if (ackCmdIndex != -1) return -1; // 把错误信息报告出来，当前由于前一个命令未收到响应，且仍然在发送


        return -1;
    }

    void acknowledgeCmd(VcardMessage msg) {

        if (msg.getCmdIndex() == ackCmdIndex) {
            mConnected = true;
            ackCmdIndex = -1; // 清除待确认命令
        }

    }

    public byte[] getSessionIvBytes() {
        return sessionIvBytes;
    }

    // 判断是否成功login
    public boolean connected() {
        return mConnected;
    }

    private VcardMessage mCommandMsg;
    private DeviceInfo mDeviceInfo;
    private byte[] sessionIvBytes; // 本次注册通信的加密向量
    private boolean mConnected;
    private short cmdIndex = 0; // 当前发生的命令cmdIndex
    private short ackCmdIndex = -1; // 需要进行命令确认的cmdIndex
    private static final byte VERSION = 0;
}