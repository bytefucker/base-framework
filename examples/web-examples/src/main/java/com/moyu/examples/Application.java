package com.moyu.examples;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 *
 * @author yihongzhi
 * @date 2022/8/18
 */
@MapperScan(basePackages = "com.moyu.examples.mapper")
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}
