package com.moyu.framework.event.core;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.UUID;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public abstract class DomainEventBase<T> {

  /**
   * 事件编号，uuid，发送方生成
   */
  private String id = UUID.randomUUID().toString();
  /**
   * 事件发布时间
   */
  private Long createTime = System.currentTimeMillis();
  /**
   * 事件描述
   */
  private String desc;
  /**
   * 领域数据
   */
  private T data;

}
