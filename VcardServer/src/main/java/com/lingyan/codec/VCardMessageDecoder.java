package com.lingyan.codec;

import com.lingyan.config.ConfigInfo;
import com.lingyan.cryption.Pkcs7Encoder;
import com.lingyan.protocol.ControlCode;
import com.lingyan.protocol.MessageHeader;
import com.lingyan.protocol.VCardMessage;
import com.lingyan.utils.BufferUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.ByteOrder;

import static com.lingyan.cryption.CRC16Util.calcCRC16;
import static com.lingyan.utils.BufferUtil.getInt;


public class VCardMessageDecoder extends LengthFieldBasedFrameDecoder {

    public static final int MAX_FRAME_LENGTH = 1024 * 1024;
    public static final int LENGTH_FIELD_LENGTH = 4;
    public static final int LENGTH_FIELD_OFFSET = 4;
    public static final int LENGTH_ADJUSTMENT = 0;
    public static final int INITIAL_BYTES_TO_STRIP = 0;

    private static final int MIX_SIZE = 14;

    public VCardMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    public VCardMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    public VCardMessageDecoder(ByteOrder byteOrder, int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(byteOrder, maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in == null || in.readableBytes() < MIX_SIZE)
            return null;

        ByteBuf frame = (ByteBuf) super.decode(ctx, in);

        // 首先，需要进行解密
        byte[] bytes = new byte[frame.readableBytes()];
        frame.readBytes(bytes);

        byte[] ivs = BufferUtil.lastBytes(bytes, ConfigInfo.IVS_COUNT);
        if (ivs == null)
            return null;

        byte[] plaintext = Pkcs7Encoder.decryptOfDiyIV(bytes, ConfigInfo.getCommKey().getBytes(), ivs);
        if (plaintext == null)
            return null;

        frame = Unpooled.copiedBuffer(plaintext);

        // 然后，进行校验
        bytes = new byte[frame.readableBytes()];
        frame.readBytes(bytes);
        byte[] originCrc16Code = new byte[16];
        originCrc16Code = BufferUtil.lastBytes(bytes, 16);
        if (originCrc16Code == null || bytes.length < 16)
            return null;

        byte[] allDataBytes = new byte[bytes.length - 16];
        int genCrc16code = calcCRC16(allDataBytes);
        int originCrc16CodeInt = getInt(originCrc16Code);

        // 不相等，校验不通过
        if (genCrc16code != originCrc16CodeInt)
            return null;


        // 最后，开始解析数据
        // 此处需要把应用数据部分转化为 ByteBuf 设置到Body里面
        frame = frame.resetReaderIndex();

        VCardMessage message = new VCardMessage();
        MessageHeader header = new MessageHeader();
        ControlCode controlCode = new ControlCode();

        header.setDeviceId(frame.readInt());
        header.setLength(frame.readInt());
        header.setInfoCode(frame.readShort());

        controlCode.setCategoryCode(frame.readByte());
        controlCode.setCommandCode(frame.readByte());

        header.setControlCode(controlCode);
        message.setHeader(header);



        return null;
    }

}
