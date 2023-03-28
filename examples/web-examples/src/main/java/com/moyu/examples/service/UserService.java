package com.moyu.examples.service;

import com.moyu.examples.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * UserService
 *
 * @author byte_fucker
 * @date 2023/3/6
 */
public interface UserService extends UserDetailsService {

  User getById(Long id);
}
