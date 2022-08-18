package com.moyu.framework.event.listener.manager;

import com.moyu.framework.event.core.DomainEventBase;
import java.util.List;

/**
 * @author
 * @date 2020-05-22 15:16
 */
public interface EventListenerManager {

  /**
   * 获取所有listener的主题
   *
   * @return 主题列表
   */
  List<String> getTopics();

  /**
   * 执行事件
   *
   * @param events 事件对象列表
   */
  void execute(List<? extends DomainEventBase<?>> events);
}
