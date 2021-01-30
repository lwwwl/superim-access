package com.example.superim.superimaccess.protocol.request;

import com.example.superim.superimaccess.annotations.TransmissionPacket;
import com.example.superim.superimaccess.protocol.Packet;
import lombok.Data;

import static com.example.superim.superimaccess.protocol.command.Command.QUIT_GROUP_REQUEST;


@Data
@TransmissionPacket(name = "QUIT_GROUP_REQUEST")
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand(){
        return QUIT_GROUP_REQUEST;
    }

}
