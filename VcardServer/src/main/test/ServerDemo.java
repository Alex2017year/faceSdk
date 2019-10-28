import com.lingyan.handler.ProtocolHandler;
import com.lingyan.protocol.MessageHeader;

import java.util.ArrayList;

public class ServerDemo {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8086;

        // 启动server
        ServerBootstrap.getInstance().init(host, port);

        // 获取所有设备的ID
        ArrayList<Integer> arrayList = ProtocolHandler.getInstance().getAllDeviceId();

        // 相关的请求
        int deviceId = 58988;
        CommandProcessor.getInstance().requestDeviceId(deviceId);





        // 关闭server
        ServerBootstrap.getInstance().shutdown();
    }
}
