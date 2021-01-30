package com.example.superim.superimaccess.protocol.request;

import com.example.superim.superimaccess.annotations.TransmissionPacket;
import com.example.superim.superimaccess.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.example.superim.superimaccess.protocol.command.Command.CREATE_GROUP_REQUEST;


@Data
@TransmissionPacket(name = "CREATE_GROUP_REQUEST")
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand(){

        return CREATE_GROUP_REQUEST;
    }

}
