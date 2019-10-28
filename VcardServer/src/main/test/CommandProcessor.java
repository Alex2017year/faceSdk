import java.util.ArrayList;

public class CommandProcessor implements ICommandProcessor {

    private CommandProcessor() {}

    public ICommandProcessor getInstance() {
        return new CommandProcessor();
    }

    @Override
    public void requestDeviceId(int deviceId) {

    }

    @Override
    public void requestDeviceBaseInfo(int deviceId) {

    }

    @Override
    public void requestSetDeviceAlias(int deviceId, String deviceAlias) {

    }

    @Override
    public void requestDeviceStatus(int deviceId) {

    }

    @Override
    public void requestSetSystemConfig(int deviceId, DeviceSystemConfig config) {

    }

    @Override
    public void requestSetSystemAllConfig(int deviceId, ArrayList<DeviceSystemConfig> configs) {

    }

    @Override
    public void requestGetSystemConfig(int deviceId) {

    }

    @Override
    public void requestSetSystemTime(int deviceId, int timestamp) {

    }

    @Override
    public void requestInitDevice(int deviceId) {

    }

    @Override
    public void requestAppUpdate(int deviceId, int versionId, String url, boolean force) {

    }

    @Override
    public void requestSetQRCodeUrl(int deviceId, String url) {

    }

    @Override
    public void requestSetKey(int deviceId, KeyFunId funId, String keys) {

    }

    @Override
    public void requestGetKey(int deviceId, KeyFunId funId) {

    }

    @Override
    public void requestSetUploadAddress(int deviceId, UploadAddressType type, String address) {

    }

    @Override
    public void requestGetUploadAddress(int deviceId, UploadAddressType type) {

    }
}
