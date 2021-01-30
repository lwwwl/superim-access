package com.example.superim.superimaccess.handler;

import com.example.superim.superimaccess.entity.LoginInfo;
import com.example.superim.superimaccess.protocol.request.LoginRequestPacket;
import com.example.superim.superimaccess.protocol.response.LoginResponsePacket;
import com.example.superim.superimaccess.session.Session;
import com.example.superim.superimaccess.util.IDUtil;
import com.example.superim.superimaccess.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 *@description  登陆请求处理类，登陆成功后用户信息存储redis
 *@author laowei
 *@date 2021/1/19 14:45
 */

@Component
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private static final Logger logger = LoggerFactory.getLogger(LoginRequestHandler.class);

    @Resource
    RedisTemplate redisTemplate;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket){
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());
        logger.info("用户{}尝试登陆...", loginRequestPacket.getUserName());
        if (valid(loginRequestPacket)){
            logger.info("用户{}登陆成功！", loginRequestPacket.getUserName());
            loginResponsePacket.setSuccess(true);
            String userId = IDUtil.randomId();
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录成功");
            LoginInfo loginInfo = loginInfoForRedis(loginRequestPacket);
            try {
                logger.info("登陆信息录入Redis...");
                // TODO 数据库操作改为异步
                redisTemplate.opsForSet().add(userId, loginInfo);
                logger.info("Redis:登陆信息录入成功！userName:{}", loginRequestPacket.getUserName());
            } catch (Exception e) {
                logger.error("Redis:登陆信息录入失败！userName:{}", loginRequestPacket.getUserName());
            }
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
        } else {
            // 数据库检验操作 TODO
            logger.info("用户登陆失败，原因：帐号密码校验失败");
            loginResponsePacket.setReason("帐号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + "：登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    /**
    *@Description: 登陆验证
    */
    private boolean valid (LoginRequestPacket loginRequestPacket){
        return true;
    }

    private LoginInfo loginInfoForRedis(LoginRequestPacket loginRequestPacket) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserName(loginRequestPacket.getUserName());
        return loginInfo;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }

}
