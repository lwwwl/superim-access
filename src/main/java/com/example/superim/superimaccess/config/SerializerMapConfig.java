package com.example.superim.superimaccess.config;

import com.example.superim.superimaccess.annotations.PacketSerializer;
import com.example.superim.superimaccess.enums.SerializerTypeEnum;
import com.example.superim.superimaccess.serializer.Serializer;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Set;

/**
 * @Description: 报文解析工具映射类(algorithmType->具体报文解析类)
 * @author: i_laowei
 * @date: 2021/1/19  10:25
 */

@Configuration
public class SerializerMapConfig {

    private static final Logger logger = LoggerFactory.getLogger(SerializerMapConfig.class);

    @Bean(name = "serializerMap")
    public HashMap<Byte, Serializer> initMap() {
        logger.info("初始化阶段开始执行serializerMap初始化...");
        HashMap<Byte, Serializer> serializerMap = new HashMap<>();
        Set<Class<?>> set = new Reflections("com.example.nettyserver").getTypesAnnotatedWith(PacketSerializer.class);
        for (Class clazz : set) {
            PacketSerializer packetSerializer = (PacketSerializer) clazz.getAnnotation(PacketSerializer.class);
            try {
                // 通过Packet枚举类获取当前PacketType对应的command值
                Byte algorithmType = SerializerTypeEnum.valueOf(packetSerializer.name()).getAlgorithmType();
                serializerMap.put(algorithmType, (Serializer) clazz.newInstance());
            } catch (Exception e) {
                logger.error("serializerMap初始化失败!Exceptions:{}", e.getMessage());
            }
        }
        logger.info("serializerMap初始化结束！");
        logger.info("serializerMap:{}", serializerMap.toString());
        return serializerMap;
    }

}
