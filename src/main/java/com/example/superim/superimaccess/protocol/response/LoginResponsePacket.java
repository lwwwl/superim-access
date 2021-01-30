package com.example.superim.superimaccess.protocol.response;

import com.example.superim.superimaccess.annotations.TransmissionPacket;
import com.example.superim.superimaccess.protocol.Packet;
import com.example.superim.superimaccess.protocol.command.Command;
import lombok.Data;

@Data
@TransmissionPacket(name = "LOGIN_RESPONSE")
public class LoginResponsePacket extends Packet {

    private String userId;

    private String userName;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
