package com.lingyan.handler;

import com.lingyan.bean.VCardDevice;
import com.lingyan.bean.VCardEvent;
import com.lingyan.event.CEventCenter;
import com.lingyan.event.Events;
import com.lingyan.event.I_CEventListener;
import com.lingyan.global.Constants;
import com.lingyan.protocol.VCardMessage;

import java.util.Map;

public class AppDataHandler implements I_CEventListener {

    private static final String[] EVENTS = {Events.EV_DEVICE_DATA, Events.EV_PACKET, Events.EV_DEVICE_STATUS};

    public void init() {
        // 注册监听消息事件
        CEventCenter.registerEventListener(this, EVENTS);
        // 启动线程接收事件
        mThread.start();
    }

    private Map<Integer, VCardDevice>  connections;

    private Runnable eventThread = new Runnable() {
        @Override
        public void run() {
            try {
                for (;;) {
                    VCardEvent event = null;
                    event = ProtocolHandler.getInstance().getData();
                    CEventCenter.dispatchEvent(Events.EV_PACKET, 0, 0, event);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };

    Thread mThread = new Thread(eventThread);

    @Override
    public void onCEvent(String topic, int msgCode, int resultCode, Object obj) {

        switch (topic) {
            case Events.EV_PACKET: {
                final VCardEvent event = (VCardEvent) obj;
                    switch (event.getType()) {
                        case TELEGRAM: {
                            VCardMessage message = event.getMessage();
                            int deviceId = message.getHeader().getDeviceId();

                            // 当前设备不在设备列表中
                            if (!connections.containsKey(deviceId)) {
                                // 设备的注册请求，
                                if (event.isRegisterReq()) {
                                    VCardDevice cardDevice = new VCardDevice();
                                    cardDevice.decodeMessage(message);
                                    cardDevice.registerResponse();

                                    // connections.put(deviceId)
                                }
                            } else { // contact with previously known device
                                // 更新设备数据

                            }
                        }
                        case DEVICE_STATUS: {
                            VCardMessage message = event.getMessage();
                            Constants.ConnectionStatus status = event.getStatus(); // 获取设备的状态更新
                            break;
                        }
                        case DELETE_DEVICE: {
                            VCardMessage message = event.getMessage();
                            int deviceId = message.getHeader().getDeviceId(); // 获取断开连接设备的ID
                            if (connections.containsKey(deviceId)) {
                                connections.remove(deviceId); // 移除相应的设备
                            }
                            break;
                        }
                        default:
                            break;
                    }
                break;
            }

            default:
                break;
        }
    }
}
