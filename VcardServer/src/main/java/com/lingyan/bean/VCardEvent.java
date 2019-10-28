package com.lingyan.bean;

import com.lingyan.global.Constants;
import com.lingyan.protocol.VCardMessage;

public class VCardEvent {
    public VCardEvent(int deviceId, Constants.ConnectionStatus status) {
        this.deviceId = deviceId;
        this.status = status;
        type = EventType.DEVICE_STATUS;
    }

    public VCardEvent(int deviceId, VCardMessage message) {
        this.deviceId = deviceId;
        this.message = message;
        this.ackRequestId = message.getHeader().getCmdSequence();
        type = EventType.TELEGRAM;
    }

    public VCardEvent(int deviceId, VCardMessage message, boolean registerReq) {
        this.deviceId = deviceId;
        this.message = message;
        this.ackRequestId = message.getHeader().getCmdSequence();
        type = EventType.TELEGRAM;
        this.registerReq = registerReq;
    }

    private int deviceId;
    private short ackRequestId;
    Constants.ConnectionStatus status;
    VCardMessage message;
    EventType type; // 事件类型
    boolean registerReq; // 是否为客户端注册请求

    // 事件类型
    public enum EventType {
        UNDEFINED((byte) 0), // 未定义
        TELEGRAM((byte) 1), // 相当于设备回复请求事件
        DEVICE_STATUS((byte) 2), // 设备主动状态事件
        DELETE_DEVICE((byte) 3); // 删除设备事件

        public byte value;
        EventType(byte value) {
            this.value = value;
        }
    }

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

    public Constants.ConnectionStatus getStatus() {
        return status;
    }

    public void setStatus(Constants.ConnectionStatus status) {
        this.status = status;
    }

    public VCardMessage getMessage() {
        return message;
    }

    public void setMessage(VCardMessage message) {
        this.message = message;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public boolean isRegisterReq() {
        return registerReq;
    }

    public void setRegisterReq(boolean registerReq) {
        this.registerReq = registerReq;
    }

    @Override
    public String toString() {
        return "VCardEvent{" +
                "deviceId=" + deviceId +
                ", ackRequestId=" + ackRequestId +
                ", status=" + status +
                ", message=" + message +
                '}';
    }
}
