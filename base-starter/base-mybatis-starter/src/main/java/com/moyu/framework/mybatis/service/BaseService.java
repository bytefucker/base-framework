package com.moyu.framework.mybatis.service;

import com.moyu.framework.core.dto.DTO;
import com.moyu.framework.core.entity.Entity;
import com.moyu.framework.core.page.Page;
import com.moyu.framework.mybatis.convert.BeanConvert;
import com.moyu.framework.mybatis.exception.DBOperationException;
import com.moyu.framework.mybatis.mapper.BaseMapper;
import com.moyu.framework.mybatis.page.PageBuilder;
import com.moyu.framework.mybatis.page.PageCondition;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BaseService
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public abstract class BaseService<D extends DTO, E extends Entity<PK>, PK extends Serializable>
    implements Service<D, E, PK> {

  private final BaseMapper<E, PK> baseMapper;
  private final BeanConvert<E, D> convert;

  public BaseService(BaseMapper<E, PK> baseMapper,
      BeanConvert<E, D> convert) {
    this.baseMapper = baseMapper;
    this.convert = convert;
  }

  /**
   * 插入数据返回主键
   *
   * @param dto
   * @return
   */
  @Override
  public PK insert(D dto) {
    E e = this.convert.convert(dto);
    if (baseMapper.insert(e) == 0) {
      throw new DBOperationException("数据插入失败");
    }
    return e.getId();
  }


  /**
   * 根据id更新数据
   *
   * @param id
   * @param dto
   */
  @Override
  public void update(PK id, D dto) {
    E e = this.convert.convert(dto);
    e.setId(id);
    if (baseMapper.updateByPrimaryKeySelective(e) == 0) {
      throw new DBOperationException("数据更新失败");
    }
  }

  /**
   * 根据id删除数据
   *
   * @param id
   */
  @Override
  public void deleteById(PK id) {
    if (baseMapper.deleteByPrimaryKey(id) == 0) {
      throw new DBOperationException("数据删除失败");
    }
  }

  /**
   * 根据id查询数据
   *
   * @param id
   * @return
   */
  @Override
  public D queryById(PK id) {
    return baseMapper.selectByPrimaryKey(id)
        .map(convert::convert).orElse(null);
  }

  /**
   * 根据条件查询列表
   *
   * @param dto
   * @return
   */
  @Override
  public List<D> list(D dto) {
    E e = this.convert.convert(dto);
    return baseMapper.selectList(e)
        .stream().map(convert::convert)
        .collect(Collectors.toList());
  }

  /**
   * 根据条件查询分页
   *
   * @param condition
   * @return
   */
  @Override
  public Page<D> page(PageCondition<D> condition) {
    E e = this.convert.convert(condition.getCondition());
    return new PageBuilder<E>()
        .pageable(condition)
        .select(() -> baseMapper.selectList(e))
        .build()
        .map(this.convert::convert);
  }


}
