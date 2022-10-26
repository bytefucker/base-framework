package com.moyu.examples.controller;

import com.moyu.examples.domain.Demo;
import com.moyu.examples.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "测试")
@RestController
@RequestMapping("/api/demo")
public class DemoController {

  private final DemoService demoService;

  public DemoController(DemoService demoService) {
    this.demoService = demoService;
  }


  @ApiOperation(value = "新增")
  @PostMapping("/")
  public Demo insert(@RequestBody Demo demo) {
    return demo;
  }

  @ApiOperation(value = "根据id查询")
  @GetMapping("/{id}")
  public Demo findById(@PathVariable Long id) {
    return demoService.findById(id);
  }

}
