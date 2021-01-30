package com.example.superim.superimaccess.protocol.response;

import com.example.superim.superimaccess.annotations.TransmissionPacket;
import com.example.superim.superimaccess.protocol.Packet;
import lombok.Data;

import static com.example.superim.superimaccess.protocol.command.Command.MESSAGE_RESPONSE;


@Data
@TransmissionPacket(name = "MESSAGE_RESPONSE")
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand(){
        return MESSAGE_RESPONSE;
    }

}
