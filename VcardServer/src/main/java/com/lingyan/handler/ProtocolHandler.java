package com.lingyan.handler;

import com.lingyan.bean.DeviceData;
import com.lingyan.bean.VCardEvent;
import com.lingyan.protocol.VCardMessage;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class ProtocolHandler implements IProtocolHandler {

    // 协议处理器
    private ProtocolHandler() {}
    private static class ProtocolHandlerInstance {
        private static final IProtocolHandler INSTANCE = new ProtocolHandler();
    }

    // 单例模式
    public static IProtocolHandler getInstance() {
        return ProtocolHandlerInstance.INSTANCE;
    }

    /// commands from the ***Handler waiting to be executed
    private BlockingQueue<VCardMessage> commands;

    /// telegram and status events waiting for pickup by
    /// the ***Handler
    private BlockingQueue<VCardEvent> events;

    @Override
    public boolean SendData(VCardMessage telegram) {
        try {
            commands.put(telegram);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // 向外抛出数据
    @Override
    public VCardEvent GetData() {
        return null;
    }

    // 向队列中添加数据
    @Override
    public void SetData(VCardEvent event) {

    }
}
