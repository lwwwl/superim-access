package com.example.superim.superimaccess.protocol.response;

import com.example.superim.superimaccess.annotations.TransmissionPacket;
import com.example.superim.superimaccess.protocol.Packet;
import lombok.Data;

import static com.example.superim.superimaccess.protocol.command.Command.JOIN_GROUP_RESPONSE;


@Data
@TransmissionPacket(name = "JOIN_GROUP_RESPONSE")
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }

}
