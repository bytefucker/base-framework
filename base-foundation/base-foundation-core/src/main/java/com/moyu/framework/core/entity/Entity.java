package com.moyu.framework.core.entity;

import java.io.Serializable;

/**
 * 数据库实体标记接口
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public interface Entity<PK extends Serializable> extends Serializable {

  PK getId();

  void setId(PK id);
}
