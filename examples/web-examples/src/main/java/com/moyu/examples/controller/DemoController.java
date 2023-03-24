package com.moyu.examples.controller;

import com.moyu.examples.domain.Demo;
import com.moyu.examples.service.DemoService;
import java.util.ArrayList;
import java.util.Date;
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

  @GetMapping("/list")
  public List<Demo> list() {
    List<Demo> list = new ArrayList<>();
    list.add(new Demo(1L, "demo", "", new Date(), "", new Date()));
    return list;
  }


  @PostMapping("/")
  public Demo insert(@RequestBody Demo demo) {
    return demo;
  }

  @GetMapping("/{id}")
  public Demo findById(@PathVariable Long id) {
    return demoService.findById(id);
  }

}
