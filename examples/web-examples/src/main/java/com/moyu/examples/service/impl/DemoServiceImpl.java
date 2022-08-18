package com.moyu.examples.service.impl;

import com.moyu.examples.domain.Demo;
import com.moyu.examples.mapper.DemoMapper;
import com.moyu.examples.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DemoServiceImpl
 *
 * @author yihongzhi
 * @date 2022/8/18
 */
@Service
public class DemoServiceImpl implements DemoService {

  @Autowired
  private DemoMapper demoMapper;


  @Override
  public Demo findById(Long id) {
    return demoMapper.selectByPrimaryKey(id).orElse(null);
  }
}
