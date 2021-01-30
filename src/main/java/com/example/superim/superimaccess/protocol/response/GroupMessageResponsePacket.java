package com.example.superim.superimaccess.protocol.response;

import com.example.superim.superimaccess.annotations.TransmissionPacket;
import com.example.superim.superimaccess.protocol.Packet;
import com.example.superim.superimaccess.session.Session;
import lombok.Data;

import static com.example.superim.superimaccess.protocol.command.Command.GROUP_MESSAGE_RESPONSE;


@Data
@TransmissionPacket(name = "GROUP_MESSAGE_RESPONSE")
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;
    private Session fromUser;
    private String message;

    @Override
    public Byte getCommand(){
        return GROUP_MESSAGE_RESPONSE;
    }

}
