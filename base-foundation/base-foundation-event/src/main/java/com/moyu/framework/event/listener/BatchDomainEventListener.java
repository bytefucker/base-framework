package com.moyu.framework.event.listener;

import com.moyu.framework.event.core.DomainEventBase;
import java.util.List;

/**
 * 批量消费事件
 *
 * @author
 * @date 2020-05-21 14:10
 */
public interface BatchDomainEventListener<T extends DomainEventBase<?>> extends EventListener {

  /**
   * 批量事件通知
   *
   * @param events 事件列表
   */
  void onEvent(List<T> events);

}
