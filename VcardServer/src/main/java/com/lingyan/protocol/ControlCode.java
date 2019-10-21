package com.lingyan.protocol;

public class ControlCode {
    private byte categoryCode; // 分类
    private byte commandCode; // 命令

    public ControlCode() { }

    public ControlCode(byte categoryCode, byte commandCode) {
        this.categoryCode = categoryCode;
        this.commandCode = commandCode;
    }

    public byte getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(byte categoryCode) {
        this.categoryCode = categoryCode;
    }

    public byte getCommandCode() {
        return commandCode;
    }

    public void setCommandCode(byte commandCode) {
        this.commandCode = commandCode;
    }

    @Override
    public String toString() {
        return "ControlCode{" +
                "categoryCode=" + categoryCode +
                ", commandCode=" + commandCode +
                '}';
    }
}
