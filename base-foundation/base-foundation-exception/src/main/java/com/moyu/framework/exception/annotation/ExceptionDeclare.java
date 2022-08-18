package com.moyu.framework.exception.annotation;

import static java.lang.annotation.ElementType.FIELD;

import com.moyu.framework.exception.BaseException;
import com.moyu.framework.exception.DefaultBaseException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionDeclare {

  Class<? extends BaseException> value() default DefaultBaseException.class;
}
