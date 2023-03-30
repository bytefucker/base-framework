package com.moyu.examples.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

/**
 * TokenLoginConfigurer
 *
 * @author yhz
 * @date 2023/3/30
 */
public class TokenLoginConfigurer<H extends HttpSecurityBuilder<H>> extends
    AbstractHttpConfigurer<TokenLoginConfigurer<H>, H> {

  public TokenLoginConfigurer() {

  }

  @Override
  public void init(H builder) throws Exception {
    super.init(builder);
  }

  @Override
  public void configure(H builder) throws Exception {
    AuthenticationManager authenticationManager = builder.getSharedObject(
        AuthenticationManager.class);
  }
}
