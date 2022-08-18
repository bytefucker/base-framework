package com.moyu.framework.format.annotation;


import com.moyu.framework.code.annotation.CodeDeclare;
import com.moyu.framework.exception.annotation.ExceptionDeclare;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorDeclare {

  CodeDeclare code();

  ExceptionDeclare exception() default @ExceptionDeclare;
}
