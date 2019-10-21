package com.lingyan;

import com.lingyan.handler.ChannelInitializerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class NettyServer {

    private String mHost;
    private int mPort;

    private EventLoopGroup mBossGroup;
    private EventLoopGroup mWorkGroup;
    private Channel mServerChannel;

    private static final Log log = LogFactory.getLog(NettyServer.class);


    private NettyServer(String hostname, int port) {
        mHost = hostname;
        mPort = port;
    }

    public void init() throws InterruptedException {
        // log.info("Starting a server...");

        mBossGroup = new NioEventLoopGroup();
        mWorkGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(mWorkGroup, mWorkGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializerHandler())
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        mServerChannel = bootstrap.bind(mHost, mPort).sync().channel();


        // log.info("Success to launch server...");
    }

    public void shutdown() throws InterruptedException {
        // log.info("Stopping server...")

        try {
            mServerChannel.close().sync();
        } finally {
            mWorkGroup.shutdownGracefully();
            mBossGroup.shutdownGracefully();
        }

        // log.info("Success to stop server..")
    }

}
