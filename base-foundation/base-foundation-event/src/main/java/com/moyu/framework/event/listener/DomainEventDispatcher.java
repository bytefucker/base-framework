package com.moyu.framework.event.listener;

/**
 * 事件转发接口
 *
 * @author
 */
public interface DomainEventDispatcher {

  /**
   * @description: 启动转发器
   * @param: []
   * @return: void
   * @author: caosujie
   * @date: 2020/5/8
   */
  void start();

  /**
   * @description: 停止转发器
   * @param: []
   * @return: void
   * @author: caosujie
   * @date: 2020/5/8
   */
  void stop();
}
