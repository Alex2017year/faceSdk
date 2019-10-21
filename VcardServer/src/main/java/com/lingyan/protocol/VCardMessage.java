package com.lingyan.protocol;

public class VCardMessage {
    private MessageHeader header; // 消息头
    private Object appData; // 消息体

    private short crc16Code; // 校验码

    public VCardMessage() { }

    public VCardMessage(MessageHeader header, Object appData) {
        this.header = header;
        this.appData = appData;
    }

    public MessageHeader getHeader() {
        return header;
    }

    public void setHeader(MessageHeader header) {
        this.header = header;
    }

    public Object getAppData() {
        return appData;
    }

    public void setAppData(Object appData) {
        this.appData = appData;
    }

    public short getCrc16Code() {
        return crc16Code;
    }

    public void setCrc16Code(short crc16Code) {
        this.crc16Code = crc16Code;
    }

    @Override
    public String toString() {
        return "VCardMessage{" +
                "header=" + header +
                '}';
    }
}
