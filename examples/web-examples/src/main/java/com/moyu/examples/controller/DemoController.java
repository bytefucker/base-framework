package com.moyu.examples.controller;

import com.moyu.examples.domain.Demo;
import com.moyu.examples.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 *
 * @author yihongzhi
 * @date 2022/8/18
 */
@RestController
@RequestMapping("/api/demo")
public class DemoController {

  @Autowired
  private DemoService demoService;

  @GetMapping("/{id}")
  public Demo findById(@PathVariable Long id) {
    return demoService.findById(id);
  }

}
