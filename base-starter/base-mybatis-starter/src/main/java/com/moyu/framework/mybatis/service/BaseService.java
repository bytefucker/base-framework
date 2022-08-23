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
public abstract class BaseService<D extends DTO, E extends Entity<PK>, PK extends Serializable> {

  private final BaseMapper<E, PK> baseMapper;
  private final BeanConvert<E, D> convert;

  public BaseService(BaseMapper<E, PK> baseMapper,
      BeanConvert<E, D> convert) {
    this.baseMapper = baseMapper;
    this.convert = convert;
  }

  public PK insert(D dto) {
    E e = this.convert.convert(dto);
    if (baseMapper.insert(e) == 0) {
      throw new DBOperationException("数据插入失败");
    }
    return e.getId();
  }


  public void update(D dto) {
    if (baseMapper.updateByPrimaryKey(convert.convert(dto)) == 0) {
      throw new DBOperationException("数据更新失败");
    }
  }

  public void deleteById(PK id) {
    if (baseMapper.deleteByPrimaryKey(id) == 0) {
      throw new DBOperationException("数据删除失败");
    }
  }

  public D queryById(PK id) {
    return baseMapper.selectByPrimaryKey(id)
        .map(convert::convert).orElse(null);
  }

  public List<D> list(D dto) {
    E e = this.convert.convert(dto);
    return baseMapper.selectList(e)
        .stream().map(convert::convert)
        .collect(Collectors.toList());
  }

  public Page<D> page(PageCondition<D> condition) {
    E e = this.convert.convert(condition.getCondition());
    return new PageBuilder<E>()
        .pageable(condition)
        .select(() -> baseMapper.selectList(e))
        .build()
        .map(this.convert::convert);
  }


}
