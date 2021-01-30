package com.example.superim.superimaccess.protocol.request;

import com.example.superim.superimaccess.annotations.TransmissionPacket;
import com.example.superim.superimaccess.protocol.Packet;
import lombok.Data;

import static com.example.superim.superimaccess.protocol.command.Command.LOGOUT_REQUEST;


@Data
@TransmissionPacket(name = "LOGOUT_REQUEST")
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST;
    }
}
