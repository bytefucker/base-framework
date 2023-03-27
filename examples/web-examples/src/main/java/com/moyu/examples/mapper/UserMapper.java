package com.moyu.examples.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moyu.examples.domain.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * UserInfoMapper
 *
 * @author byte_fucker
 * @date 2023/3/6
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

  @Select(value = "SELECT * FROM `user` where login_name=#{loginName} and deleted=0")
  User findByLoginName(@Param("loginName") String loginName);

}
