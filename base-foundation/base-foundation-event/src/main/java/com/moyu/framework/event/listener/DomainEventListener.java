package com.moyu.framework.event.listener;

import com.moyu.framework.event.core.DomainEventBase;

/**
 * 事件监听接口
 *
 * @author
 */
public interface DomainEventListener<T extends DomainEventBase<?>> extends EventListener {

  /**
   * 事件触发
   *
   * @param t event
   */
  void onEvent(T t);
}
