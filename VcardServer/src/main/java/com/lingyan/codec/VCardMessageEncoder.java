package com.lingyan.codec;

import com.lingyan.protocol.VCardMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class VCardMessageEncoder extends MessageToByteEncoder<VCardMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, VCardMessage vCardMessage, ByteBuf sendBuf) throws Exception {
        if (vCardMessage == null || vCardMessage.getHeader() == null)
            throw new Exception("The encode message is null");


    }

    private byte[] encrypt(byte[] plaintext) {

        return null;
    }

    private short genCrc16Code(byte[] data) {

        return 0;
    }



}
