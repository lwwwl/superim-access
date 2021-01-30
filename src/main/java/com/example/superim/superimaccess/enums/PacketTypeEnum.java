package com.example.superim.superimaccess.enums;

/**
 * @Description: Command->报文类型 映射枚举类
 * @author: i_laowei
 * @date: 2021/1/19  9:59
 */
public enum PacketTypeEnum {

    LOGIN_REQUEST((byte) 1),

    LOGIN_RESPONSE((byte) 2),

    MESSAGE_REQUEST((byte) 3),

    MESSAGE_RESPONSE((byte) 4),

    LOGOUT_REQUEST((byte) 5),

    LOGOUT_RESPONSE((byte) 6),

    CREATE_GROUP_REQUEST((byte) 7),

    CREATE_GROUP_RESPONSE((byte) 8),

    LIST_GROUP_MEMBERS_REQUEST((byte) 9),

    LIST_GROUP_MEMBERS_RESPONSE((byte) 10),

    JOIN_GROUP_REQUEST((byte) 11),

    JOIN_GROUP_RESPONSE((byte) 12),

    QUIT_GROUP_REQUEST((byte) 13),

    QUIT_GROUP_RESPONSE((byte) 14),

    GROUP_MESSAGE_REQUEST((byte) 15),

    GROUP_MESSAGE_RESPONSE((byte) 16);

    private byte command;

    PacketTypeEnum(byte command) {
        this.command = command;
    }

    public byte getCommand() {
        return command;
    }

    public void setCommand(byte command) {
        this.command = command;
    }

}
