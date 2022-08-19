package com.moyu.framework.mybatis.service;

import io.mybatis.mapper.Mapper;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BaseService
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
@Service
public abstract class BaseService<T, PK extends Serializable> {

  @Autowired
  private Mapper<T, PK> mapper;


}
