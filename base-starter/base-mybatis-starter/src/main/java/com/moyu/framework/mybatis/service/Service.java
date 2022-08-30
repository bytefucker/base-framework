package com.moyu.framework.mybatis.service;

import com.moyu.framework.core.dto.DTO;
import com.moyu.framework.core.entity.Entity;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Service
 *
 * @author yihongzhi
 * @date 2022/8/23
 */
public interface Service<E extends Entity<PK>, PK extends Serializable, D extends DTO> {

  /**
   * 插入数据返回主键
   *
   * @param dto
   * @return
   */
  PK insert(D dto);


  /**
   * 批量插入
   *
   * @param list
   */
  void batchInsert(List<D> list);

  /**
   * 根据id更新数据
   *
   * @param id
   * @param dto
   */
  void updateById(PK id, D dto);

  /**
   * 根据id删除数据
   *
   * @param id
   */
  void deleteById(PK id);

  /**
   * 根据id查询数据
   *
   * @param id
   * @return
   */
  Optional<D> queryById(PK id);

  /**
   * 根据条件查询列表
   *
   * @param dto
   * @return
   */
  List<D> list(D dto);
}
