package com.lingyan.bean;

import com.lingyan.global.Constant;
import com.lingyan.protocol.VCardMessage;

public class VCardEvent {

    public VCardEvent(int deviceId, Constant.ConnectionStatus status) {
        this.deviceId = deviceId;
        this.status = status;
    }

    public VCardEvent(int deviceId, VCardMessage message) {
        this.deviceId = deviceId;
        this.message = message;
    }

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

    private int deviceId;
    private boolean acknowledge;
    Constant.ConnectionStatus status;
    VCardMessage message;


    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isAcknowledge() {
        return acknowledge;
    }

    public void setAcknowledge(boolean acknowledge) {
        this.acknowledge = acknowledge;
    }

    public Constant.ConnectionStatus getStatus() {
        return status;
    }

    public void setStatus(Constant.ConnectionStatus status) {
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
                ", acknowledge=" + acknowledge +
                ", status=" + status +
                ", message=" + message +
                '}';
    }
}
