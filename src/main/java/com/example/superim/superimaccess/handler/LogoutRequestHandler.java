package com.example.superim.superimaccess.handler;

import com.example.superim.superimaccess.annotations.RequestHandler;
import com.example.superim.superimaccess.protocol.request.LogoutRequestPacket;
import com.example.superim.superimaccess.protocol.response.LogoutResponsePacket;
import com.example.superim.superimaccess.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

@Component
@RequestHandler(name = "LOGOUT_REQUEST")
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg){
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }

}
