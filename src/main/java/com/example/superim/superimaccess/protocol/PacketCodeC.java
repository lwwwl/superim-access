package com.example.superim.superimaccess.protocol;

import com.example.superim.superimaccess.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 *@description  报文解析器
 *@author laowei
 *@date 2021/1/19 10:54
 */

@Component
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;

    @Resource(name = "packetTypeMap")
    HashMap<Byte, Class<? extends Packet>> packetTypeMap;

    @Resource(name = "serializerMap")
    HashMap<Byte, Serializer> serializerMap;

    public void encode(ByteBuf byteBuf, Packet packet) {

        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

    }

    public Packet decode(ByteBuf byteBuf) {

        // 跳过魔数
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null){
            return serializer.deserialize(requestType, bytes);
        }

        return null;

    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);

    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);

    }
}




















