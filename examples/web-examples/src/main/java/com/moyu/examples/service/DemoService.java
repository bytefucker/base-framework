package com.moyu.examples.service;

import com.moyu.examples.domain.Demo;
import java.util.List;

/**
 * DemoService
 *
 * @author yihongzhi
 * @date 2022/8/18
 */
public interface DemoService {

  Demo findById(Long id);

  Demo insert(Demo demo);


  List<Demo> list();
}
