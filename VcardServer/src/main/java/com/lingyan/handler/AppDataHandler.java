package com.lingyan.handler;

import com.lingyan.bean.VCardDevice;
import com.lingyan.bean.VCardEvent;
import com.lingyan.event.CEventCenter;
import com.lingyan.event.Events;
import com.lingyan.event.I_CEventListener;
import com.lingyan.protocol.VCardMessage;

import java.util.Map;

public class AppDataHandler implements I_CEventListener {

    public void startThread() {
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
                            break;
                        }
                        case DEVICE_STATUS: {

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
