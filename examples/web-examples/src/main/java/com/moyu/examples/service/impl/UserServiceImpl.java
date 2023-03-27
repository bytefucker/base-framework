package com.moyu.examples.service.impl;


import com.moyu.examples.domain.model.User;
import com.moyu.examples.mapper.PermissionMapper;
import com.moyu.examples.mapper.UserMapper;
import com.moyu.examples.service.UserService;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author byte_fucker
 * @date 2023/3/6
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final PermissionMapper permissionMapper;

  public UserServiceImpl(UserMapper userMapper,
      PermissionMapper permissionMapper) {
    this.userMapper = userMapper;
    this.permissionMapper = permissionMapper;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userMapper.findByLoginName(username);
    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException(username);
    }
    return org.springframework.security.core.userdetails.User.builder()
        .username(username)
        .password(user.getPassword())
        .authorities(List.of())
        //.disabled(Enabled.FALSE.getValue().equals(user.getEnable()))
        .build();
  }
}
