package com.lingyan.handler;

import com.lingyan.bean.VCardEvent;
import com.lingyan.protocol.VCardMessage;

public interface IProtocolHandler {
    boolean SendData(VCardMessage telegram);
    VCardEvent GetData();
    void SetData(VCardEvent event);
}
