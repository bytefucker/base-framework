package com.moyu.examples.controller;

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


  @GetMapping("/{id}")
  public Object demo(@PathVariable String id) {
    return "demo";
  }

}
