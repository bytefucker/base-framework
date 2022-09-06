package com.moyu.framework.mybatis.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisConfig
 *
 * @author yihongzhi
 * @date 2022/9/2
 */
@Configuration
@AutoConfigureBefore(value = MybatisAutoConfiguration.class)
public class MybatisConfig {

  @Bean
  public ConfigurationCustomizer mybatisConfigurationCustomizer() {
    return new ConfigurationCustomizer() {
      /**
       * Customize the given a {@link Configuration} object.
       *
       * @param configuration the configuration object to customize
       */
      @Override
      public void customize(org.apache.ibatis.session.Configuration configuration) {
        //configuration.addInterceptor(new MybatisInterceptor());
      }
    };
  }
}
