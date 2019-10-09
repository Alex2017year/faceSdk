package com.face.net_ph;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ClientHandle implements Runnable {
    private String mHost;
    private int mPort;
    private Selector mSelector;
    private SocketChannel mSocketChannel;
    private volatile boolean mStarted;
    private boolean mConnected = false;
    private ICallbackInterface mCallbackInterface;

    public ClientHandle(String ip, int port) {
        this.mHost = ip;
        this.mPort = port;

        try {
            // 创建选择器
            mSelector = Selector.open();
            // 打开监听通道
            mSocketChannel = SocketChannel.open();
            // 将通道设置为非阻塞模式
            mSocketChannel.configureBlocking(false);
            mStarted = true;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void register(ICallbackInterface callbackInterface) {
        mCallbackInterface = callbackInterface;
    }

    public void stop() {
        mStarted = false;
    }

    public boolean isConnected() {
        return mConnected;
    }

    @Override
    public void run() {
        try {
            doConnection();
        } catch (IOException e) {
            e.printStackTrace();
            mStarted = false;
        }

        // 循环遍历selector
        while (mStarted) {
            try {
                // 无论是否有读写事件发生，selector每隔1s被唤醒一次
                mSelector.select(1000);
                Set<SelectionKey> keys = mSelector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();

                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        // selector 关闭后会自动是否里面管理的资源
        if (mSelector != null) {
            try {
                mSelector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doConnection() throws IOException {
        if (mSocketChannel.connect(new InetSocketAddress(mHost, mPort)));
        else mSocketChannel.register(mSelector, SelectionKey.OP_CONNECT);
    }

    public void sendMsg(String msg) throws Exception {
        mSocketChannel.register(mSelector, SelectionKey.OP_READ);
        doWrite(mSocketChannel, msg);
    }

    public void sendMsg(byte[] msg) throws Exception {
        mSocketChannel.register(mSelector, SelectionKey.OP_READ);
        doWrite(mSocketChannel, msg);
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                boolean ret = sc.finishConnect();
                mConnected = ret;
                if (!ret) {
                    key.cancel();
                    key.attach(null);
                    key.channel().close();
                }
            }

            // 读消息
            if (key.isReadable()) {
                // 创建ByteBuffer，并开辟一个1M的缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(buffer);
                if (readBytes > 0) {
                    // 将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes); // buffer 里面就是接收到服务器端的数据
                    mCallbackInterface.handleMsg(bytes);
                } else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    // 异步发送消息
    private void doWrite(SocketChannel channel, String request) throws IOException {
        byte[] bytes = request.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }

    private void doWrite(SocketChannel channel, byte[] request) throws IOException {
        ByteBuffer writeBuffer = ByteBuffer.allocate(request.length);
        writeBuffer.put(request);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }

}
