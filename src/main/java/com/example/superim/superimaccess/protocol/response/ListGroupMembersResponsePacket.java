package com.example.superim.superimaccess.protocol.response;

import com.example.superim.superimaccess.annotations.TransmissionPacket;
import com.example.superim.superimaccess.protocol.Packet;
import com.example.superim.superimaccess.session.Session;
import lombok.Data;

import java.util.List;

import static com.example.superim.superimaccess.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

@Data
@TransmissionPacket(name = "LIST_GROUP_MEMBERS_RESPONSE")
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }

}
