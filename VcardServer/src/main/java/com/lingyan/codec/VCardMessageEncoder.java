package com.lingyan.codec;

import com.lingyan.protocol.VCardMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class VCardMessageEncoder extends MessageToByteEncoder<VCardMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, VCardMessage vCardMessage, ByteBuf byteBuf) throws Exception {

    }
}
