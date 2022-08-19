package com.moyu.framework.mybatis.service;

import com.moyu.framework.core.page.PageCondition;
import com.moyu.framework.core.page.PageResult;
import com.moyu.framework.mybatis.convert.BeanConvert;
import com.moyu.framework.mybatis.dto.DTO;
import com.moyu.framework.mybatis.entity.Entity;
import com.moyu.framework.mybatis.mapper.BaseMapper;
import java.io.Serializable;
import java.util.List;

/**
 * BaseService
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public abstract class BaseService<D extends DTO, E extends Entity<PK>, PK extends Serializable> {

  private final BaseMapper<E, PK> baseMapper;
  private final BeanConvert<E, D> convert;

  public BaseService(BaseMapper<E, PK> baseMapper,
      BeanConvert<E, D> convert) {
    this.baseMapper = baseMapper;
    this.convert = convert;
  }

  /**
   * 查询用户列表
   *
   * @param dto
   * @return
   */

  public List<D> list(D dto) {
    return null;
  }

  /**
   * 分页查询系统用户
   *
   * @param condition
   * @return
   */

  public PageResult<D> page(PageCondition<D> condition) {
    return null;
  }

  /**
   * 查询用户信息
   *
   * @param id
   * @return
   */

  public D queryById(PK id) {
    //SysUser sysUser = sysUserMapper.selectByPrimaryKey(id).orElse(null);
    return null;
  }

  /**
   * 新增用户
   *
   * @param dto
   * @return
   */

  public D insert(D dto) {
    return null;
  }

  /**
   * 更新用户
   *
   * @param dto
   * @return
   */

  public D update(D dto) {
    return null;
  }

  /**
   * 删除用户
   *
   * @param id
   * @return
   */

  public D deleteById(PK id) {
    return null;
  }

}
