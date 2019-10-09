package com.face.service_ph;

import com.face.control.IMessageFormatter;
import com.face.control.VcardMessageFormatter;

public class ProtocolHandler {

    public ProtocolHandler() { }

    public void initialize(String ip, int port) {
        boolean ret = Protocol.getInstance().initialize(mFormatter, ip, port);
        if (!ret) assert false: "初始化失败！";
    }



    /// launched as thread - continuously reads events from real-time module
    /// and posts them as notifications
    private void ReadMessages() {

    }

    // 用于激活首次通信
    public void activate() {

    }

    public void handleMsg() {

    }

    private IMessageFormatter mFormatter = new VcardMessageFormatter();
}
