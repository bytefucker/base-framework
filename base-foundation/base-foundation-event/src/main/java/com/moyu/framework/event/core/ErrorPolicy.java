package com.moyu.framework.event.core;

public enum ErrorPolicy {
  /**
   * 跳过抛异常的消息，继续执行
   */
  SkipError,
  /**
   * 停止消费当前Partition剩余的消息，直到下一次poll，从有问题的点开始消费
   */
  NextPoll,
}
