package com.lingyan.cache;

import com.lingyan.protocol.VCardMessage;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeviceManager {

    // 保存客户端Channel
    // 设备ID <---> Channel 之间建立对应关系
    private static Map<String, Channel> channelMap = new ConcurrentHashMap<>();


}
