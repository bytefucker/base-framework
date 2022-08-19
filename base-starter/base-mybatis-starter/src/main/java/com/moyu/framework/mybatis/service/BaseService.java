package com.moyu.framework.mybatis.service;

import com.moyu.framework.mybatis.mapper.BaseMapper;
import com.moyu.framework.mybatis.model.BaseEntity;
import java.io.Serializable;

/**
 * BaseService
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public abstract class BaseService<T extends BaseEntity<PK>, PK extends Serializable> {

  private final BaseMapper<T, PK> mapper;

  protected BaseService(BaseMapper<T, PK> mapper) {
    this.mapper = mapper;
  }
}
