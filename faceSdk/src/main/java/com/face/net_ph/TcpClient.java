package com.face.net_ph;

public class TcpClient {
    private ClientHandle mClientHandle;

    public TcpClient(String ip, int port, ICallbackInterface readHandler) {
        mClientHandle = new ClientHandle(ip, port);
        mClientHandle.register(readHandler);
    }

    public void startClient() {
        new Thread(mClientHandle,"Client").start();
    }

    public void sendMessage(String msg) throws Exception {
        mClientHandle.sendMsg(msg);
    }

    public void sendMessage(byte[] msg) throws Exception {
        mClientHandle.sendMsg(msg);
    }

    public boolean isConnected() {
        return mClientHandle.isConnected();
    }

    public void stop() {
        mClientHandle.stop();
    }
}


