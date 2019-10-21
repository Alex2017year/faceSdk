package com.lingyan.global;

public class Constant {

    public static enum ConnectionState {
        HEALTHY((byte) 0),
        COM_FAILED((byte) 1);

        public byte value;
        private ConnectionState(byte value) {
            this.value = value;
        }
    }
}
