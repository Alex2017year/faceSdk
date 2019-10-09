package com.face.control;

import com.face.model.VcardMessage;

public interface IMessageFormatter {
    boolean msgToBuffer(VcardMessage msg, byte[] buffer);
    boolean bufferToMsg(byte[] buffer, VcardMessage msg);
}
