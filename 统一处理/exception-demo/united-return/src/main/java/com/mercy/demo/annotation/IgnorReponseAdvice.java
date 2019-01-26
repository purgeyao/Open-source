package com.mercy.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * run 生效 {方法生效，类生效}
 *
 * @author zhaoyao
 * @version 1.0
 * @date 2019-01-26
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnorReponseAdvice {

}
