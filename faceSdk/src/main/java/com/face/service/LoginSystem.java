package com.face.service;

import com.face.model.VcardMessage;

public class LoginSystem {
    public LoginSystem() {
        commandMsg = new VcardMessage();
        commandMsg.deviceId = 0;
        commandMsg.categoryCode = (byte) 0xFF;
        commandMsg.commandCode = 0;
        commandMsg.data = new byte[]{(byte) 0x14};
    }

    public void registerDevice() {

    }

    private VcardMessage commandMsg;

}
