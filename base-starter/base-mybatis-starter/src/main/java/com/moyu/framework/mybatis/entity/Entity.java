package com.moyu.framework.mybatis.entity;

import java.io.Serializable;

/**
 * Entity
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public interface Entity<PK extends Serializable> {

  PK getId();

  void setId(PK id);

}
