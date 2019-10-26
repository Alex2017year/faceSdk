package com.lingyan.bean;

import com.lingyan.global.Constants;
import com.lingyan.protocol.VCardMessage;

public class VCardEvent {

    public VCardEvent(int deviceId, Constants.ConnectionStatus status) {
        this.deviceId = deviceId;
        this.status = status;
    }

    public VCardEvent(int deviceId, VCardMessage message) {
        this.deviceId = deviceId;
        this.message = message;
        this.ackRequestId = message.getHeader().getCmdSequence();

    }

    private int deviceId;
    private short ackRequestId;
    Constants.ConnectionStatus status;
    VCardMessage message;

    // 事件类型
    public enum EventType {
        UNDEFINED((byte) 0), // 未定义
        TELEGRAM((byte) 1), // 相当于设备回复请求
        DEVICE_STATUS((byte) 2); // 设备主动上报

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
