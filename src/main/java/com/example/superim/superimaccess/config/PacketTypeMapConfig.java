package com.example.superim.superimaccess.config;

import com.example.nettyserver.netty.annotations.TransmissionPacket;
import com.example.nettyserver.netty.enums.PacketTypeEnum;
import com.example.nettyserver.netty.protocol.Packet;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Set;

/**
 * @Description: init阶段构造packetTypeMap的map映射, command值->具体packet实例对象
 * @author: i_laowei
 * @date: 2021/1/19  9:50
 */

@SuppressWarnings("unchecked")
@Configuration
public class PacketTypeMapConfig {

    private static final Logger logger = LoggerFactory.getLogger(PacketTypeEnum.class);

    @Bean(name = "packetTypeMap")
    public HashMap<Byte, Class<? extends Packet>> initMap() {
        logger.info("初始化阶段开始执行packetTypeMap初始化...");
        HashMap<Byte, Class<? extends Packet>> packetTypeMap = new HashMap<>();
        Set<Class<?>> set = new Reflections("com.example.nettyserver").getTypesAnnotatedWith(TransmissionPacket.class);
        for (Class clazz : set) {
            TransmissionPacket transmissionPacket = (TransmissionPacket) clazz.getAnnotation(TransmissionPacket.class);
            try {
                // 通过Packet枚举类获取当前PacketType对应的command值
                Byte command = PacketTypeEnum.valueOf(transmissionPacket.name()).getCommand();
                packetTypeMap.put(command, clazz);
            } catch (Exception e) {
                logger.error("packetTypeMap初始化失败!Exceptions:{}", e.getMessage());
                e.printStackTrace();
            }
        }
        logger.info("packetTypeMap初始化结束！");
        logger.info("packetTypeMap:{}", packetTypeMap.toString());
        return packetTypeMap;
    }

}
