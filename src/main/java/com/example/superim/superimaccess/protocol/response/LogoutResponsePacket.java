package com.example.superim.superimaccess.protocol.response;

import com.example.superim.superimaccess.annotations.TransmissionPacket;
import com.example.superim.superimaccess.protocol.Packet;
import lombok.Data;

import static com.example.superim.superimaccess.protocol.command.Command.LOGOUT_RESPONSE;


@Data
@TransmissionPacket(name = "LOGOUT_RESPONSE")
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
