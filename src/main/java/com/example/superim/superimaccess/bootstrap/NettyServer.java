package com.example.superim.superimaccess.bootstrap;

import com.example.superim.superimaccess.codec.PacketCodecHandler;
import com.example.superim.superimaccess.codec.Spliter;
import com.example.superim.superimaccess.handler.AuthHandler;
import com.example.superim.superimaccess.handler.IMHandler;
import com.example.superim.superimaccess.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *@description  nettyServer启动类
 *@author laowei
 *@date 2021/1/18 11:47
 */

@Component
public class NettyServer {

    @Resource
    IMHandler imHandler;

    @Resource
    PacketCodecHandler packetCodecHandler;

    @Resource
    LoginRequestHandler loginRequestHandler;

    @Resource
    AuthHandler authHandler;

    public void start(int port) {
        final NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch){
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(packetCodecHandler);
                        ch.pipeline().addLast(loginRequestHandler);
                        ch.pipeline().addLast(authHandler);
                        ch.pipeline().addLast(imHandler);
                    }
                });
        bind(serverBootstrap, port);
    }

    private void bind(final ServerBootstrap serverBootstrap, final int port){
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()){
                System.out.println("端口绑定成功！");
            } else {
                System.out.println("端口绑定失败！");
            }
        });
    }
}
