package com.example.superim.superimaccess.handler;

import com.example.superim.superimaccess.annotations.RequestHandler;
import com.example.superim.superimaccess.protocol.request.QuitGroupRequestPacket;
import com.example.superim.superimaccess.protocol.response.QuitGroupResponsePacket;
import com.example.superim.superimaccess.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.springframework.stereotype.Component;

@Component
@RequestHandler(name = "QUIT_GROUP_REQUEST")
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket){
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移出
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        // 2. 构造退群响应发送给客户端
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();

        responsePacket.setGroupId(requestPacket.getGroupId());
        responsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
