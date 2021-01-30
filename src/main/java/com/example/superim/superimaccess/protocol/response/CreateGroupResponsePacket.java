package com.example.superim.superimaccess.protocol.response;

import com.example.superim.superimaccess.annotations.TransmissionPacket;
import com.example.superim.superimaccess.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.example.superim.superimaccess.protocol.command.Command.CREATE_GROUP_RESPONSE;


@Data
@TransmissionPacket(name = "CREATE_GROUP_RESPONSE")
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand(){

        return CREATE_GROUP_RESPONSE;
    }

}
