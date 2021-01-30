package com.example.superim.superimaccess.handler;

import com.example.superim.superimaccess.annotations.RequestHandler;
import com.example.superim.superimaccess.protocol.request.ListGroupMembersRequestPacket;
import com.example.superim.superimaccess.protocol.response.ListGroupMembersResponsePacket;
import com.example.superim.superimaccess.session.Session;
import com.example.superim.superimaccess.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequestHandler(name = "LIST_GROUP_MEMBERS_REQUEST")
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket requestPacket){
        // 1. 获取群的 ChannelGroup
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        // 2. 遍历群成员的 channel，对应的 session，构造群成员的信息
        List<Session> sessionList = new ArrayList<>();
        for (Channel channel : channelGroup){
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }

        System.out.println("Hello!");

        // 3. 构建获取成员列表响应写回到客户端
        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();

        responsePacket.setGroupId(groupId);
        responsePacket.setSessionList(sessionList);
        ctx.channel().writeAndFlush(responsePacket);
    }

}