package com.moyu.framework.event.publisher;

import com.moyu.framework.event.core.DomainEventBase;

/**
 * 事件发布接口
 *
 * @param <T>
 * @author caosujie
 */
public interface DomainEventPublisher<T extends DomainEventBase<?>> {

  /**
   * 发布领域事件
   *
   * @param t 事件对象
   */
  void publish(T t);

}
