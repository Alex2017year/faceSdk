package com.face.service_ph;

import com.face.model.Constants;
import com.face.model.VcardMessage;

public class ProtocolEvent {

    public ProtocolEvent(Constants.ControlCategory category, VcardMessage msg) {
        mMessage = msg;
        mControlCategory = category;
    }

    public boolean GetAcknowledge() {

        return false;
    }

    public void SetAcknowledge(boolean left) {

    }

    public VcardMessage GetTelegram() {

        return null;
    }

    /// Set if telegram is a command acknowledgment
    private boolean mAcknowledge;

    /// 标识哪个命令
    Constants.ControlCategory mControlCategory;

    /// telegram contents
    private VcardMessage mMessage;
}
