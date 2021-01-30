package com.example.superim.superimaccess.config;

import com.example.superim.superimaccess.annotations.RequestHandler;
import com.example.superim.superimaccess.enums.PacketTypeEnum;
import com.example.superim.superimaccess.protocol.Packet;
import io.netty.channel.SimpleChannelInboundHandler;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Set;

/**
 * @Description: init阶段构造requestHandler的map映射, command值->具体handler实例对象
 * @author: i_laowei
 * @date: 2021/1/18  10:10
 */

@SuppressWarnings("unchecked")
@Configuration
public class HandlerMapConfig {

    private static final Logger logger = LoggerFactory.getLogger(HandlerMapConfig.class);

    @Bean(name = "handlerMap")
    public HashMap<Byte, SimpleChannelInboundHandler<? extends Packet>> initMap() {
        logger.info("初始化阶段开始执行handlerMap初始化...");
        HashMap<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap = new HashMap<>();
        Set<Class<?>> set = new Reflections("com.example.nettyserver").getTypesAnnotatedWith(RequestHandler.class);
        for (Class clazz : set) {
            RequestHandler requestHandler = (RequestHandler) clazz.getAnnotation(RequestHandler.class);
            try {
                // 通过Packet枚举类获取当前PacketType对应的command值
                Byte command = PacketTypeEnum.valueOf(requestHandler.name()).getCommand();
                handlerMap.put(command, (SimpleChannelInboundHandler<? extends Packet>)clazz.newInstance());
            } catch (Exception e) {
                logger.error("handlerMap初始化失败!Exceptions:{}", e.getMessage());
            }
        }
        logger.info("handlerMap初始化结束！");
        logger.info("handlerMap:{}", handlerMap.toString());
        return handlerMap;
    }

}
