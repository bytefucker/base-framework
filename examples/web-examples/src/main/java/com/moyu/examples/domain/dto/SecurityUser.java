package com.moyu.examples.domain.dto;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * SecurityUser
 *
 * @author byte_fucker
 * @date 2023/3/7
 */
public class SecurityUser implements UserDetails {

  private final String username;

  private final String password;

  private final boolean isEnable;

  private final List<GrantedAuthority> authorities;


  public SecurityUser(String username, String password, boolean isEnable,
      List<GrantedAuthority> authorities) {
    this.username = username;
    this.password = password;
    this.isEnable = isEnable;
    this.authorities = authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.isEnable;
  }
}
