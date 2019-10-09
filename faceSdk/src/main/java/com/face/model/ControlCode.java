package com.face.model;

public class ControlCode {
    public ControlCode(byte category, byte cmdCode) {
        this.mCategory = category;
        this.mCmdCode = cmdCode;
    }

    private byte mCategory;
    private byte mCmdCode;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof  ControlCode)) return false;

        final ControlCode cc = (ControlCode) obj;
        return this.mCmdCode == cc.mCmdCode && this.mCategory == cc.mCategory;
    }

    @Override
    public String toString() {
        return "ControlCode{" +
                "category=" + mCategory +
                ", cmdCode=" + mCmdCode +
                '}';
    }

    public byte getmCategory() {
        return mCategory;
    }

    public void setmCategory(byte category) {
        this.mCategory = category;
    }

    public byte getmCmdCode() {
        return mCmdCode;
    }

    public void setmCmdCode(byte cmdCode) {
        this.mCmdCode = cmdCode;
    }
}
