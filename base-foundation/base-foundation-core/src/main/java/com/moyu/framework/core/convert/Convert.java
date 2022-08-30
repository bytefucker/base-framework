package com.moyu.framework.core.convert;

import com.moyu.framework.core.dto.DTO;
import com.moyu.framework.core.entity.Entity;

/**
 * 实体,DTO转换接口
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public interface Convert<E extends Entity<?>, D extends DTO> {

  /**
   * DTO转数据库实体
   *
   * @param dto
   * @return
   */
  E toEntity(D dto);

  /**
   * 数据库实体转DTO
   *
   * @param entity
   * @return
   */
  D toDTO(E entity);

}
