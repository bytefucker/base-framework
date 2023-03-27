package com.moyu.examples.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeController
 *
 * @author byte_fucker
 * @date 2023/3/6
 */
@RestController
public class HomeController {

  @RequestMapping("/")
  public Object index() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication;
  }

}
