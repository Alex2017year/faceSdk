package com.lingyan.interf;

import com.lingyan.protocol.VCardMessage;
import io.netty.buffer.ByteBuf;

public interface IDevice {
    // 应用层把数据放入，相当于接收来自应用层准备发送的数据
    void putData(ByteBuf data);

    // 应用层解析数据放入这里
    void decodeMessage(VCardMessage msg);
}
