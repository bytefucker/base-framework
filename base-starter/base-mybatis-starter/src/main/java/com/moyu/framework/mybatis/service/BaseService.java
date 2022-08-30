package com.moyu.framework.mybatis.service;

import com.moyu.framework.core.convert.Convert;
import com.moyu.framework.core.dto.DTO;
import com.moyu.framework.core.entity.Entity;
import com.moyu.framework.mybatis.exception.DBOperationException;
import com.moyu.framework.mybatis.mapper.BaseMapper;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BaseService
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public abstract class BaseService<E extends Entity<PK>, PK extends Serializable, D extends DTO>
    implements Service<E, PK, D> {

  @Autowired
  private BaseMapper<E, PK> baseMapper;
  @Autowired
  private Convert<E, D> convert;

  /**
   * 插入数据返回主键
   *
   * @param dto
   * @return
   */
  @Override
  public PK insert(D dto) {
    E e = this.convert.toEntity(dto);
    if (baseMapper.insert(e) == 0) {
      throw new DBOperationException("数据插入失败");
    }
    return e.getId();
  }

  /**
   * 批量插入
   *
   * @param list
   */
  @Override
  public void batchInsert(List<D> list) {
    List<E> collect = list.stream().map(convert::toEntity).collect(Collectors.toList());
    if (baseMapper.insertList(collect) == 0) {
      throw new DBOperationException("数据批量插入失败");
    }
  }

  /**
   * 根据id更新数据
   *
   * @param id
   * @param dto
   */
  @Override
  public void updateById(PK id, D dto) {
    E e = this.convert.toEntity(dto);
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
  public Optional<D> queryById(PK id) {
    return baseMapper.selectByPrimaryKey(id).map(convert::toDTO);
  }

  /**
   * 根据条件查询列表
   *
   * @param dto
   * @return
   */
  @Override
  public List<D> list(D dto) {
    E e = this.convert.toEntity(dto);
    return baseMapper.selectList(e)
        .stream().map(convert::toDTO)
        .collect(Collectors.toList());
  }
}
