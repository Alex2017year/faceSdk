package com.face.service_ph;

import com.face.control.IMessageFormatter;
import com.face.model.Constants;
import com.face.model.VcardMessage;
import com.face.net_ph.ICallbackInterface;
import com.face.net_ph.TcpClient;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Protocol implements ICallbackInterface {

    public enum StatusEnum { HEALTHY, COM_FAILED, ACTIVATION_PENDING }
    private static Protocol instance = new Protocol();
    private Protocol() {}

    public static Protocol getInstance(){
        return instance;
    }

    // 初始化
    public boolean initialize(IMessageFormatter formatter, String ip, int port) {
        this.mFormatter = formatter;
        if( mFormatter == null )
            return false;

        mSocket = new TcpClient(ip, port, this);
        mCmds = new LinkedBlockingQueue();
        return true;
    }

    // 开始建立通信
    public int startComm(VcardMessage msg) {

        return 0;
    }

    // 向服务器端发送命令请求
    public void sendData(VcardMessage telegram) {
        try {
            mCmds.put(telegram);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ProtocolEvent getData() {
        // take it off the queue
        ProtocolEvent event = null;
        while( event == null )
        {
            try {
                event = mEvents.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return event;
    }

    @Override
    public void handleMsg(byte[] buffer) {
        if (buffer == null) return;
        VcardMessage msg = null;
        if (mFormatter.bufferToMsg(buffer, msg)) {
            mStatus = StatusEnum.HEALTHY;

            byte category = msg.getCategoryCode();
            if (Constants.ControlCategory.isValidCategory(category)) {
                ProtocolEvent event = new ProtocolEvent(new Constants.ControlCategory(category), msg);
                // 不能阻塞在这里
                if(!mEvents.offer(event)){
                    // FIXME: 需要进一步处理，或者记录下来
                }
            }
        }
    }

    public void writerThread() {
        mSocket.startClient(); // 开始建立通信

        while(true)  {
            if (mStatus == StatusEnum.HEALTHY) {
                if (!mCmds.isEmpty()) {
                    try {
                        VcardMessage msg = mCmds.take(); // 这里需要做互斥处理！！
                        writeToSocket(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /// writes the given message to the VcardMessage socket(s)
    public void  writeToSocket(VcardMessage msg) {
        if (mSocket.isConnected()){
            mStatus = StatusEnum.HEALTHY;

            byte[] buffer = null;
            if(mFormatter.msgToBuffer(msg, buffer))
            {
                try {
                    if (buffer == null) return;
                    mSocket.sendMessage(buffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            mStatus = StatusEnum.COM_FAILED;
            // 需要进行重连。。。 调用接口重连
        }
    }

    private StatusEnum mStatus; // 通信状态
    private IMessageFormatter mFormatter; // 通信数据对象格式化类
    BlockingQueue<ProtocolEvent> mEvents; // 收到服务器端的信息
    BlockingQueue<VcardMessage> mCmds; // 发送给服务器端的数据
    TcpClient mSocket;
}
