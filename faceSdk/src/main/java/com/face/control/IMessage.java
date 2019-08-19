package com.face.control;

import com.face.model.VcardMessage;

public interface IMessage {
    byte[] packMessage(VcardMessage msg);
    VcardMessage unpackMessage(byte[] data);

    void send(byte[] data);
    void append(byte[] data);
    byte[] getContent();
}
