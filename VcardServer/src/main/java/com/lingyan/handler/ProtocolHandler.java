package com.lingyan.handler;

import com.lingyan.bean.DeviceData;
import com.lingyan.bean.IPAddressPair;
import com.lingyan.bean.VCardEvent;
import com.lingyan.global.Constants;
import com.lingyan.protocol.VCardMessage;
import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

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

    /// telegram and status events waiting for pickup by
    /// the ***Handler
    private BlockingQueue<VCardEvent> events = new LinkedBlockingQueue<>();

    // 设备信息表
    // deviceId <---> DeviceData
    private Map<Integer, DeviceData> deviceTable = new ConcurrentHashMap<>();

    // channel <---> DeviceData
    private Map<Channel, DeviceData> channelDeviceIdTable = new ConcurrentHashMap<>();

    @Override
    public void removeBadDevice(Channel channel) {
        if (channelDeviceIdTable.containsKey(channel)) {
            DeviceData dd = channelDeviceIdTable.remove(channel);
            if (deviceTable.containsKey(dd.getDeviceId())) {
                deviceTable.remove(dd.getDeviceId());
            }
        }
    }

    @Override
    public DeviceData find(Channel channel) {
        if (channelDeviceIdTable.containsKey(channel)) {
            return channelDeviceIdTable.get(channel);
        }
        return null;
    }

    @Override
    public void addNewDevice(Channel channel, VCardMessage message) {
        // 首次出现的设备
        if (!channelDeviceIdTable.containsKey(channel)) {
            DeviceData deviceData = new DeviceData(message.getHeader().getDeviceId());
            deviceData.setStatus(Constants.ConnectionStatus.HEALTHY);

            deviceData.setChannel(channel);
            InetSocketAddress address = (InetSocketAddress) channel.localAddress();
            deviceData.setCurIPAddress(new IPAddressPair(address.getHostName(), address.getPort()));

            channelDeviceIdTable.put(channel, deviceData);
            deviceTable.put(deviceData.getDeviceId(), deviceData);

            setData(new VCardEvent(deviceData.getDeviceId(), message, true));
        }
    }

    // 仅仅处理已经正常通信的数据
    @Override
    public void dealDeviceData(Channel channel, VCardMessage message) {
        int deviceId = message.getHeader().getDeviceId();
        if (deviceTable.containsKey(deviceId)) {
            DeviceData deviceData = deviceTable.get(deviceId);
            if (deviceData.getStatus() == Constants.ConnectionStatus.COM_FAILED) {
                // 设备的上一次状态是com_failed, 所以这次必须触发一个登陆请求
                return;
            }

            deviceData.setStatus(Constants.ConnectionStatus.HEALTHY);
            if (deviceData.getChannel() != channel) {
                // log("")
                // 某个设备通道变化了，说明IP和port变化，需要及时更新
                deviceData.setChannel(channel);
                InetSocketAddress address = (InetSocketAddress) channel.localAddress();
                deviceData.setCurIPAddress(new IPAddressPair(address.getHostName(), address.getPort()));
            }

            // 更新设备状态
            setData(new VCardEvent(deviceId, Constants.ConnectionStatus.HEALTHY));

            // 发送数据报文
            setData(new VCardEvent(deviceId, message));
        }
    }

    @Override
    public boolean sendData(VCardMessage telegram) {
        if (telegram == null || telegram.getHeader() == null)
            return false;

        int deviceId = telegram.getHeader().getDeviceId();

        // 当前设备ID，并不在设备属性表中
        if (!deviceTable.containsKey(deviceId))
            return false;

        // 当前设备断开连接
        if (deviceTable.get(deviceId).getStatus() == Constants.ConnectionStatus.COM_FAILED)
            return false;

        // 开始发送数据
        deviceTable.get(deviceId).getChannel().writeAndFlush(telegram);
        return true;
    }

    // 向外抛出数据
    // 外界线程中不断进行读取操作
    @Override
    public VCardEvent getData() throws InterruptedException {
        return events.take();
    }

    // 向队列中添加数据
    public void setData(VCardEvent event) {
        try {
            events.put(event);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
