package com.moyu.examples.controller;

import com.moyu.framework.format.DefaultErrorDefine;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeController
 *
 * @author byte_fucker
 * @date 2023/3/6
 */
@RestController
public class HomeController {

  @GetMapping("/")
  public Authentication index() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication;
  }

  @GetMapping("/exception")
  public void exception() {
    throw DefaultErrorDefine.SERVER_ERROR.exception();
  }

}
