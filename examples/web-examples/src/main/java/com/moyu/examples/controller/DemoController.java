package com.moyu.examples.controller;

import com.moyu.examples.domain.Demo;
import com.moyu.examples.service.DemoService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  private final DemoService demoService;

  public DemoController(DemoService demoService) {
    this.demoService = demoService;
  }


  @GetMapping("/exception")
  public List<Demo> exception() {
    throw new RuntimeException("demo");
  }

  @GetMapping("/list")
  public List<Demo> list() {
    return demoService.list();
  }


  @PostMapping("/")
  public Demo insert(@RequestBody Demo demo) {
    return demoService.insert(demo);
  }

  @GetMapping("/{id}")
  public Demo findById(@PathVariable Long id) {
    return demoService.findById(id);
  }

}
