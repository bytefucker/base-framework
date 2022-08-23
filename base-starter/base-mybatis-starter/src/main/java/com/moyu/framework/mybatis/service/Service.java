package com.moyu.framework.mybatis.service;

import com.moyu.framework.core.dto.DTO;
import com.moyu.framework.core.entity.Entity;
import com.moyu.framework.core.page.Page;
import com.moyu.framework.mybatis.page.PageCondition;
import java.io.Serializable;
import java.util.List;

/**
 * Service
 *
 * @author yihongzhi
 * @date 2022/8/23
 */
public interface Service<D extends DTO, E extends Entity<PK>, PK extends Serializable> {

  /**
   * 插入数据返回主键
   *
   * @param dto
   * @return
   */
  PK insert(D dto);

  /**
   * 根据id更新数据
   *
   * @param id
   * @param dto
   */
  void update(PK id, D dto);

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
  D queryById(PK id);

  /**
   * 根据条件查询列表
   *
   * @param dto
   * @return
   */
  List<D> list(D dto);

  /**
   * 根据条件查询分页
   *
   * @param condition
   * @return
   */
  Page<D> page(PageCondition<D> condition);
}
