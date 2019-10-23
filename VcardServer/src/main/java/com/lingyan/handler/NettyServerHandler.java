package com.lingyan.handler;

import com.lingyan.bean.DeviceData;
import com.lingyan.bean.IPAddressPair;
import com.lingyan.bean.VCardEvent;
import com.lingyan.global.Constant;
import com.lingyan.protocol.VCardMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyServerHandler extends SimpleChannelInboundHandler<VCardMessage> {

    // data for all known remote devices
    private Map<Integer, DeviceData> devices = new ConcurrentHashMap<>();

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, VCardMessage vcardMessage) throws Exception {

        int deviceId = vcardMessage.getHeader().getDeviceId();
        Channel channel = channelHandlerContext.channel();
        if (devices.containsKey(deviceId))
        {

        } else {
            DeviceData deviceData = new DeviceData(deviceId);
            deviceData.setCmdTelegram(vcardMessage);
            deviceData.setStatus(Constant.ConnectionStatus.HEALTHY);
            deviceData.setChannel(channel);
            InetSocketAddress address = (InetSocketAddress) channel.localAddress();
            deviceData.setCurIPAddress(new IPAddressPair(address.getHostName(), address.getPort()));
            deviceData.setAckRequestId(vcardMessage.getHeader().getCmdSequence());
            devices.put(deviceId, deviceData);
        }

        VCardEvent event = new VCardEvent(deviceId, vcardMessage);
        event.setMessage(vcardMessage);

        ProtocolHandler.getInstance().SetData(event);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }
}
