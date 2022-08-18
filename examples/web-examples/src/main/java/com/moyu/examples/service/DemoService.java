package com.moyu.examples.service;

import com.moyu.examples.domain.Demo;

/**
 * DemoService
 *
 * @author yihongzhi
 * @date 2022/8/18
 */
public interface DemoService {

  Demo findById(Long id);

  Demo insert(Demo demo);
}
