package com.lingyan.handler;

import com.lingyan.bean.DeviceData;
import com.lingyan.bean.VCardEvent;
import com.lingyan.protocol.VCardMessage;
import io.netty.channel.Channel;

public interface IProtocolHandler {
    // void addDevice(int deviceId);

    // 查找通道是否，有对应的 DeviceData
    void removeBadDevice(Channel channel);
    DeviceData find(Channel channel);
    void addNewDevice(Channel channel, VCardMessage message);
    void dealDeviceData(Channel channel, VCardMessage message);
    boolean sendData(VCardMessage telegram);
    VCardEvent getData();
}
