import com.lingyan.bean.AuthorizationCardInfo;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;

public interface ICommandProcessor {

    enum KeyFunId {
        CARD_READER_KEY(0), // 读卡器密钥
        QR_CODE_KEY(1), // 二维码密钥
        OEMCODE_THIRDPARTY_DEVICE(2); // OEMCode_Thirdparty_Device

        private int value;
        public int getValue() {
            return this.value;
        }

        KeyFunId(int value) {
            this.value = value;
        }
    }

    enum UploadAddressType {
        GOTHROUGH_SNAPSHOT_UPLOAD_ADDRESS(0),
        STRANGER_SNAPSHOT_UPLOAD_ADDRESS(1),
        BACKLIST_SNAPSHOT_UPLOAD_ADDRESS(2),
        RECORD_UPLOAD_ADDRESS(3),
        DOORBELL_SNAPSHOT_ADDRESS(4);

        private int value;
        int getValue() { return this.value; }
        UploadAddressType(int value) {
            this.value = value;
        }
    }

    enum LockMode {
        CLOSE_DOOR_LOCK(0),
        OPEN_DOOR_LOCK(1),
        CANCEL_LOCK(2);

        int value;
        public int getValue() { return this.value; }
        LockMode(int value) {
            this.value = value;
        }
    }

    enum LocalLockType {
        SETTABLE(0),
        FORBID_ENTER_LOCAL_SETTING(1),
        UNABLE_SET(2),
        TOUCH_INVALID(3);

        int value;
        public int getValue() { return this.value; }
        LocalLockType(int value) {
            this.value = value;
        }
    }

    // 设备参数设置
    void requestDeviceId(int deviceId);
    void requestDeviceBaseInfo(int deviceId);
    void requestSetDeviceAlias(int deviceId, String deviceAlias);
    void requestDeviceStatus(int deviceId);
    void requestSetSystemConfig(int deviceId, DeviceSystemConfig config);
    void requestSetSystemAllConfig(int deviceId, ArrayList<DeviceSystemConfig> configs);
    void requestGetSystemConfig(int deviceId);
    void requestSetSystemTime(int deviceId, int timestamp);
    void requestInitDevice(int deviceId);
    void requestAppUpdate(int deviceId, int versionId, String url, boolean force);
    void requestSetQRCodeUrl(int deviceId, String url);
    void requestSetKey(int deviceId, KeyFunId funId, String keys);
    void requestGetKey(int deviceId, KeyFunId funId);
    void requestSetUploadAddress(int deviceId, UploadAddressType type, String address);
    void requestGetUploadAddress(int deviceId, UploadAddressType type);

    // 设备控制命令
    void requestRelayControl(int deviceId, int relayId);
    void requestRelayControlWithDelay(int deviceId, int relayId, int delay);
    void requestLockDevice(int deviceId, LockMode mode);
    void requestRebootDevice(int deviceId);
    void requestSetLocalLock(int deviceId, LocalLockType type);
    void requestRelayControlWithORCode(int deviceId, int relayId, int random, int personId);
    void requestRelayControlWithRemoteDoor(int deviceId, int relayId, int personId);
    void requestRelayControlWithRemoteDoorandDelay(int deviceId, int relayId, int personId, int delay);

    // 授权卡
    void requestClearAllAuthorizationCard(int deviceId);
    void requestReadSingleAuthorizationCard(int deviceId, long cardId);
    void requestAddAuthorizationCard(int deviceId, AuthorizationCardInfo[] cardInfo);
    void requestDeleteAuthorizationCard(int deviceId, long[] cardId);
    void requestDeleteAuthorizationCardByPersonId(int deviceId, int[] personId);

    // 通行记录&事件


}
