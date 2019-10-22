package com.lingyan.bean;

import com.lingyan.global.Constant;
import com.lingyan.protocol.VCardMessage;
import io.netty.channel.Channel;

public class DeviceData {

    public DeviceData(int deviceId) {
        this.deviceId = deviceId;
        this.ackRequestId = 0; // 默认的请求id是0
        this.status = Constant.ConnectionStatus.COM_FAILED;
    }

    private int deviceId; // 设备唯一标识
    private short ackRequestId; // 等待确认的RequestId
    private VCardMessage cmdTelegram; // 存储来自SDK接口的命令请求
    private VCardMessage pollTelegram; // 轮询响应，客户端发送了一个polling，服务器就回复相应的pollData
    private Channel channel; // 设备连接通道信息
    private IPAddressPair curIPAddress; // 可以向外界提供一个调试的方法；
    private Constant.ConnectionStatus status; // 设备的通信状态

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public short getAckRequestId() {
        return ackRequestId;
    }

    public void setAckRequestId(short ackRequestId) {
        this.ackRequestId = ackRequestId;
    }

    public VCardMessage getCmdTelegram() {
        return cmdTelegram;
    }

    public void setCmdTelegram(VCardMessage cmdTelegram) {
        this.cmdTelegram = cmdTelegram;
    }

    public VCardMessage getPollTelegram() {
        return pollTelegram;
    }

    public void setPollTelegram(VCardMessage pollTelegram) {
        this.pollTelegram = pollTelegram;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public IPAddressPair getCurIPAddress() {
        return curIPAddress;
    }

    public void setCurIPAddress(IPAddressPair curIPAddress) {
        this.curIPAddress = curIPAddress;
    }

    public Constant.ConnectionStatus getStatus() {
        return status;
    }

    public void setStatus(Constant.ConnectionStatus status) {
        this.status = status;
    }
}
