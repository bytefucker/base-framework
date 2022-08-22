package com.moyu.framework.mybatis.service;

import com.moyu.framework.mybatis.page.PageBuilder;
import com.moyu.framework.mybatis.page.PageCondition;
import com.moyu.framework.core.page.Page;
import com.moyu.framework.mybatis.convert.BeanConvert;
import com.moyu.framework.mybatis.dto.DTO;
import com.moyu.framework.mybatis.entity.Entity;
import com.moyu.framework.mybatis.mapper.BaseMapper;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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
    return baseMapper.selectList(convert.convert(dto))
        .stream().map(convert::convert)
        .collect(Collectors.toList());
  }

  /**
   * 分页查询系统用户
   *
   * @param condition
   * @return
   */

  public Page<D> page(PageCondition<D> condition) {
    return new PageBuilder<E>()
        .pageable(condition)
        .select(() -> baseMapper.selectList(convert.convert(condition.getCondition())))
        .build()
        .map(convert::convert);
  }

  /**
   * 查询用户信息
   *
   * @param id
   * @return
   */

  public D queryById(PK id) {
    return baseMapper.selectByPrimaryKey(id)
        .map(convert::convert).orElse(null);
  }

  /**
   * 新增用户
   *
   * @param dto
   * @return
   */

  public D insert(D dto) {
    E e = this.convert.convert(dto);
    baseMapper.insert(e);
    return convert.convert(e);
  }

  /**
   * 更新用户
   *
   * @param dto
   * @return
   */

  public int update(D dto) {
    return baseMapper.updateByPrimaryKey(convert.convert(dto));
  }

  /**
   * 删除用户
   *
   * @param id
   * @return
   */

  public int deleteById(PK id) {
    return baseMapper.deleteByPrimaryKey(id);
  }

}
