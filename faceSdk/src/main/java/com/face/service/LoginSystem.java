package com.face.service;

import com.face.model.Constants;
import com.face.model.VcardMessage;

// 用于向服务器发起注册请求
public class LoginSystem {
    public LoginSystem() {
        commandMsg = new VcardMessage(true);
        commandMsg.setDeviceId(0);
        commandMsg.setCategoryCode(Constants.UNIVERSAL_CATEGORY);
        commandMsg.setCommandCode(Constants.COMMAND_01);
        commandMsg.setData(new byte[]{(byte) 0x14});
        commandMsg.setInfoCode((short)0); // 这里存在问题，需要指定下
    }

    public void registerDevice() {

    }

    private VcardMessage commandMsg;

}
