package com.example.superim.superimaccess.handler;

import com.example.superim.superimaccess.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

@Component
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    @Resource(name = "handlerMap")
    HashMap<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception{
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
