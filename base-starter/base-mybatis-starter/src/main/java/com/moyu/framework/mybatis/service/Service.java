package com.moyu.framework.mybatis.service;

import com.moyu.framework.core.entity.Entity;
import com.moyu.framework.core.vo.VO;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Service
 *
 * @author yihongzhi
 * @date 2022/8/23
 */
public interface Service<E extends Entity<PK>, PK extends Serializable, V extends VO> {

  /**
   * 插入数据返回主键
   *
   * @param vo
   * @return
   */
  PK insert(V vo);


  /**
   * 批量插入
   *
   * @param list
   */
  void batchInsert(List<V> list);

  /**
   * 根据id更新数据
   *
   * @param id
   * @param vo
   */
  void updateById(PK id, V vo);

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
  Optional<V> queryById(PK id);
}
