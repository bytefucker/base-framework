package com.moyu.framework.mybatis.service;

import com.moyu.framework.core.convert.Convert;
import com.moyu.framework.core.entity.Entity;
import com.moyu.framework.core.vo.VO;
import com.moyu.framework.mybatis.exception.DBOperationException;
import com.moyu.framework.mybatis.mapper.BaseMapper;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 增删改查基础service
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public abstract class BaseService<E extends Entity<PK>, PK extends Serializable, V extends VO>
    implements Service<E, PK, V> ,Convert<E,V> {

  private final BaseMapper<E, PK> baseMapper;

  public BaseService(BaseMapper<E, PK> baseMapper) {
    this.baseMapper = baseMapper;
  }

  /**
   * 插入数据返回主键
   *
   * @param vo
   * @return
   */
  @Override
  public PK insert(V vo) {
    E e = this.mapTo(vo);
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
  public void batchInsert(List<V> list) {
    List<E> collect = list.stream()
        .map(this::mapTo).collect(Collectors.toList());
    if (baseMapper.insertList(collect) != list.size()) {
      throw new DBOperationException("数据批量插入失败");
    }
  }

  /**
   * 根据id更新数据
   *
   * @param id
   * @param vo
   */
  @Override
  public void updateById(PK id, V vo) {
    E e =this.mapTo(vo);
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
  public Optional<V> queryById(PK id) {
    return baseMapper.selectByPrimaryKey(id).map(this::mapTo);
  }
}
