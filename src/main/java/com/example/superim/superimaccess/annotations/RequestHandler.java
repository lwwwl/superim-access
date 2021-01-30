package com.example.superim.superimaccess.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 请求处理类注解
 * @author: i_laowei
 * @date: 2021/1/18  9:46
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestHandler {

    String name();

}
