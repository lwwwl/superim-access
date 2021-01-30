package com.example.superim.superimaccess.codec;

import com.example.superim.superimaccess.protocol.Packet;
import com.example.superim.superimaccess.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    @Resource
    PacketCodeC packetCodeC;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out){
        out.add(packetCodeC.decode(byteBuf));
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> out){
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        packetCodeC.encode(byteBuf, packet);
        out.add(byteBuf);
    }

}
