package com.moyu.examples.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moyu.examples.domain.model.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * Role
 *
 * @author byte_fucker
 * @date 2023/3/6
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
