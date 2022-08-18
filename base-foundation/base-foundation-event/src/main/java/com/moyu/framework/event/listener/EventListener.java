package com.moyu.framework.event.listener;

/**
 * @author
 * @date 2020-05-22 15:16
 */
public interface EventListener {

  /**
   * 消费组，设置不同的消费组可以达到广播事件的效果
   *
   * @return groupId
   */
  default String getGroupId() {
    return null;
  }

}
