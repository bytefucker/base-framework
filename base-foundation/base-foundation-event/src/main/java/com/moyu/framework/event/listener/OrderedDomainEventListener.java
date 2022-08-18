package com.moyu.framework.event.listener;


import com.moyu.framework.event.core.DomainEventBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 顺序消费接口
 *
 * @author
 * @date 2020-06-17 17:46
 **/
public interface OrderedDomainEventListener<T extends DomainEventBase<?>> extends EventListener {

  Logger log = LoggerFactory.getLogger(OrderedDomainEventListener.class);

  /**
   * 事件触发
   *
   * @param t event
   */
  void onEvent(T t);

  /**
   * 事件处理异常回调
   *
   * @param t 事件主题
   * @param e 异常
   */
  default void onException(T t, Exception e) {
    log.error("consumer msg error, msg:{}", t, e);
  }

}
