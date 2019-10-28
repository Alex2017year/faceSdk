package com.lingyan.bean;

public class AuthorizationCardInfo {

    private static final byte BIT_FLAG = (byte)0x0F;

    public AuthorizationCardInfo() { }

    private long cardId;
    private int password;
    private int permissionValidity;
    private int openDoorDuration;
    private int reserve1;
    private int permissionStartTime;
    private byte residualInCount;
    private byte residualOutCount;
    private short reserve2;
    private byte type;
    private byte permission; // 这里包含两部分：开门权限 + 特殊权限
    private byte openDoorPermission; // 开门权限
    private byte specialPermission; // 特殊权限
    private byte reserve3;
    private byte state;
    private int personId;


    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getPermissionValidity() {
        return permissionValidity;
    }

    public void setPermissionValidity(int permissionValidity) {
        this.permissionValidity = permissionValidity;
    }

    public int getOpenDoorDuration() {
        return openDoorDuration;
    }

    public void setOpenDoorDuration(int openDoorDuration) {
        this.openDoorDuration = openDoorDuration;
    }

    public int getPermissionStartTime() {
        return permissionStartTime;
    }

    public void setPermissionStartTime(int permissionStartTime) {
        this.permissionStartTime = permissionStartTime;
    }

    public byte getResidualInCount() {
        return residualInCount;
    }

    public void setResidualInCount(byte residualInCount) {
        this.residualInCount = residualInCount;
    }

    public byte getResidualOutCount() {
        return residualOutCount;
    }

    public void setResidualOutCount(byte residualOutCount) {
        this.residualOutCount = residualOutCount;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPermission() {
        return permission;
    }

    public void setPermission(byte permission) {
        this.permission = permission;

        byte permission1 = (byte) (this.permission & BIT_FLAG);
        byte permission2 = (byte) ((this.permission >>> 4) & BIT_FLAG);
        this.openDoorPermission = permission1; // TODO: 这里需要进一步确定
        this.specialPermission = permission2; // TODO : 需要确定哪一个半字节表示permission
    }

    public void setPermission(byte openDoorPermission, byte specialPermission) {
        // 将两个特殊的位进行合并操作
        this.permission =
    }

    public byte getOpenDoorPermission() {
        return openDoorPermission;
    }

    public byte getSpecialPermission() {
        return specialPermission;
    }


    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
