package com.example.superim.superimaccess.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 报文解析类注解
 * @author: i_laowei
 * @date: 2021/1/19  10:16
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PacketSerializer {

    String name();

}
