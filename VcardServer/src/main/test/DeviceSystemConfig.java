public class DeviceSystemConfig {

    private static final int WHITE_LIGHT_WIDTH_RATIO_MAX_VALUE = 50;
    private static final int RED_LIGHT_WIDTH_RATIO_MAX_VALUE = 20;
    private static final int AUTO_REBOOT_MAX_VALUE = 23;
    private static final int ENTERING_SCREENSAVER_MIN_TIME = 5;
    private static final int ENTERING_SCREENSAVER_MAX_TIME = 60;

    public DeviceSystemConfig(ConfigAddress configAddress, int configValue) {
        this.configAddress = configAddress;
        this.configValue = configValue;
    }

    public DeviceSystemConfig() {}

    // 相关属性字段设置
    private ConfigAddress configAddress;
    private int configValue;

    public enum UploadMode {
        CONTINUE_SHIELD(0),
        FIXED_TIME_INTERVAL(1);

        public int getValue() {
            return this.value;
        }

        private int value;
        UploadMode(int value) {
            this.value = value;
        }
    }


    public enum Switch {
        ON(0x25),
        OFF(0x19);

        private int value;
        Switch(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    // 设备配置属性
    public enum ConfigAddress {
        AUTOMATIC_SCREEN_SWITCH(0x01),
        SCREENSAVER_FACE_IDENTIFICATION_SWITCH(0x02),
        STRANGER_RECORD_SWITCH(0x03),
        STRANGER_RECORD_UPLOAD_INTERVAL(0x04),
        STRANGER_RECORD_UPLOAD_model(0x05),
        DOORBELL_SWITCH(0x06),
        PHOTOSENSITIVE_LIGHT_OFF_THRESHOLD(0x07),
        PHOTOSENSITIVE_LIGHT_ON_THRESHOLD(0x08),
        SET_PASSWORD(0x09),
        POPUP_DIALOG_DURATION(0x0A),
        WHITE_LIGHT_HIGHLIGHT_WIDE_PERCENTAGE(0x0B),
        WHITE_LIGHT_LOWLIGHT_WIDE_PERCENTAGE(0x0C),
        RED_LIGHT_HIGHLIGHT_WIDE_PERCENTAGE(0x0D),
        RED_LIGHT_LOWLIGHT_WIDE_PERCENTAGE(0x0E),
        STRANGE_FACE_HINT_SWITCH(0x0F),
        AUTO_REBOOT_SWITCH(0x10),
        AUTO_REBOOT_TIME(0x11),
        AUTO_SCREENSAVER_ANIMATION_SWITCH(0x12),
        AUTO_ENTERING_SCREENSAVER_TIME(0x13),
        VOICE_SWITCH(0x14),
        WIPING_CARD_AND_FACE_SWITCH(0x15),
        WHETHER_ADD_CARD_AUTHORIZATION(0x16);

        private int value;
        public int getValue() {
            return value;
        }

        ConfigAddress(int value) {
            this.value = value;
        }
    }

    public ConfigAddress getConfigAddress() {
        return configAddress;
    }

    public void setConfigAddress(ConfigAddress configAddress) {
        this.configAddress = configAddress;
    }

    public int getConfigValue() {
        return configValue;
    }

    public void setConfigValue(int configValue) {

        int newConfigValue = configValue;

        switch (this.configAddress) {
            case WHITE_LIGHT_HIGHLIGHT_WIDE_PERCENTAGE:
            case WHITE_LIGHT_LOWLIGHT_WIDE_PERCENTAGE:
            {
                if (configValue > WHITE_LIGHT_WIDTH_RATIO_MAX_VALUE)
                    newConfigValue = WHITE_LIGHT_WIDTH_RATIO_MAX_VALUE;
                break;
            }
            case RED_LIGHT_HIGHLIGHT_WIDE_PERCENTAGE:
            case RED_LIGHT_LOWLIGHT_WIDE_PERCENTAGE:
            {
                if (configValue > RED_LIGHT_WIDTH_RATIO_MAX_VALUE)
                    newConfigValue = RED_LIGHT_WIDTH_RATIO_MAX_VALUE;
                break;
            }
            case AUTO_REBOOT_TIME:
            {
                if (configValue > AUTO_REBOOT_MAX_VALUE)
                    newConfigValue = AUTO_REBOOT_MAX_VALUE;

                break;
            }
            case AUTO_ENTERING_SCREENSAVER_TIME:
            {
                if (configValue > ENTERING_SCREENSAVER_MAX_TIME)
                    newConfigValue = ENTERING_SCREENSAVER_MAX_TIME;
                if (configValue < ENTERING_SCREENSAVER_MIN_TIME)
                    newConfigValue = ENTERING_SCREENSAVER_MIN_TIME;
                break;
            }
            default:
                break;
        }

        this.configValue = newConfigValue;
    }

    // 配置开关变量
    public void setConfigValue(Switch sw) {
        this.configValue = sw.getValue();
    }

    // 上传模式变量
    public void setConfigValue(UploadMode mode) {
        this.configValue = mode.getValue();
    }

}
