package com.lingyan.bean;

import com.lingyan.global.Constants;
import com.lingyan.handler.ProtocolHandler;
import com.lingyan.interf.IDevice;
import com.lingyan.protocol.VCardMessage;
import io.netty.buffer.ByteBuf;

import java.util.Queue;

public class VCardDevice implements IDevice {
    private Queue<VCardMessage> commandList;
    private VCardMessage currentCmd;
    private boolean active; //查看设备是否激活

    // 注册请求的response
    public void registerResponse() {

    }

    // 设备的通信状态
    private Constants.ConnectionStatus commStatus;

    // 确认命令
    public void acknowledgeCmd(VCardMessage msg) {

    }

    @Override
    public void putData(ByteBuf data) {

    }

    @Override
    public void decodeMessage(VCardMessage msg) {

    }

    private void sendCmd(VCardMessage cmdMsg) {
        // 发送请求命令，相当于调用全局
        ProtocolHandler.getInstance().sendData(cmdMsg);
    }

    public void resendCmd() {

    }
}
