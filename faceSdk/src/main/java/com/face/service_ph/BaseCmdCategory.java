package com.face.service_ph;

import com.face.model.VcardMessage;

import java.util.Deque;

public abstract class BaseCmdCategory {

    // 用于解码接收到的数据
    public abstract void DecodeMessage(VcardMessage msg);

    // 发送命令数据
    public void SendCmd(VcardMessage msg) {

    }

    // 将数据部分写入
    public abstract void putData(byte[] data);

    public void ResendCmd() {

    }

    // 对命令进行确认，把确认从队列命令中移除
    public void acknowledgeCmd(VcardMessage msg) {

    }

    private static int mDeviceId = 0;
    private Deque<VcardMessage> mCommandList;
    private VcardMessage mCurrentCmd;
}
