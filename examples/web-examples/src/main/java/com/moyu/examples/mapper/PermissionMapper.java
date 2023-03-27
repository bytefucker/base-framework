package com.moyu.examples.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moyu.examples.domain.model.Permission;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * PermissionMapper
 *
 * @author byte_fucker
 * @date 2023/3/6
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

  List<Permission> getPermissionByRoleIds(@Param("ids") List<Long> ids);

}
