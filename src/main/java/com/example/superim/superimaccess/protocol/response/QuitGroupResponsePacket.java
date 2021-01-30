package com.example.superim.superimaccess.protocol.response;

import com.example.superim.superimaccess.annotations.TransmissionPacket;
import com.example.superim.superimaccess.protocol.Packet;
import lombok.Data;

import static com.example.superim.superimaccess.protocol.command.Command.QUIT_GROUP_RESPONSE;


@Data
@TransmissionPacket(name = "QUIT_GROUP_RESPONSE")
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }

}
