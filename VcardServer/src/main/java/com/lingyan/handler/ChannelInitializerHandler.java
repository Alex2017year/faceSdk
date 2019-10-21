package com.lingyan.handler;

import com.lingyan.codec.VCardMessageEncoder;
import com.lingyan.codec.VCardMessageDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.nio.ByteOrder;

import static com.lingyan.codec.VCardMessageDecoder.*;

public class ChannelInitializerHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("encoder", new VCardMessageDecoder(ByteOrder.BIG_ENDIAN, MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET,
                LENGTH_FIELD_LENGTH, LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP, true));
        pipeline.addLast("decoder", new VCardMessageEncoder());
        pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
        pipeline.addLast(new LoginAuthRespHandler());
        pipeline.addFirst("heartBeatHandler", new HeartBeatRespHandler());

    }
}
