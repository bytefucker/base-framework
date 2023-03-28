package com.moyu.examples.controller;

import com.moyu.examples.domain.model.User;
import com.moyu.examples.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 *
 * @author yhz
 * @date 2023/3/28
 */
@RestController
@RequestMapping("/api")
public class ApiController {

  private final UserService userService;

  public ApiController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user/{id}")
  public User getById(@PathVariable Long id) {
    return userService.getById(id);
  }

}
