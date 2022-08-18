package com.moyu.framework.message;

/**
 * 消息接口类
 */
public interface IMessage {

  String getMessage(String name);

  String getMessage(String name, String defaultValue);

}
