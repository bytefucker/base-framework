package com.moyu.framework.event.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author
 * @date 2020-05-08 19:29
 **/
@Data
public class EventConfig {

  /**
   * 消费组，组内的消息不会重复消费
   */
  @Value("${cestc.framework.event.group.id:EVENT-DEFAULT-GROUP}")
  private String groupId;
  /**
   * 核心线程数量
   */
  @Value("${cestc.framework.event.core.pool.size:10}")
  private int corePoolSize;
  /**
   * 最大线程数量
   */
  @Value("${cestc.framework.event.maximum.pool.size:20}")
  private int maximumPoolSize;
  /**
   * 非核心线程的最大存活时间
   */
  @Value("${cestc.framework.event.keepalive.time:60}")
  private int keepAliveTime;
  /**
   * 阻塞队列大小
   */
  @Value("${cestc.framework.event.blocking.queue.size:50}")
  private int blockingQueueSize;

  /**
   * 失败重试次数
   */
  @Value("${cestc.framework.event.attempt:5}")
  private int attempt;
  /**
   * 重试最大等待时间（min），即重试时间
   */
  @Value("${cestc.framework.event.maximum.time:5}")
  private int maximumTime;
  /**
   * 指数 乘数
   */
  @Value("${cestc.framework.event.multiplier:1}")
  private int multiplier;
}
