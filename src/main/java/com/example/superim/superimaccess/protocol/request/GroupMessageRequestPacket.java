package com.example.superim.superimaccess.protocol.request;

import com.example.superim.superimaccess.annotations.TransmissionPacket;
import com.example.superim.superimaccess.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.superim.superimaccess.protocol.command.Command.GROUP_MESSAGE_REQUEST;

@Data
@TransmissionPacket(name = "GROUP_MESSAGE_REQUEST")
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;
    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message){
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand(){
        return GROUP_MESSAGE_REQUEST;
    }

}
