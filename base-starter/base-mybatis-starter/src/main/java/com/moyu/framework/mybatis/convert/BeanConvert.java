package com.moyu.framework.mybatis.convert;

import com.moyu.framework.mybatis.dto.DTO;
import com.moyu.framework.mybatis.entity.Entity;

/**
 * 实体转换接口
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public interface BeanConvert<E extends Entity, D extends DTO> {

  /**
   * DTO转数据库实体
   *
   * @param dto
   * @return
   */
  E convert(D dto);

  /**
   * 数据库实体转DTO
   *
   * @param entity
   * @return
   */
  D convert(E entity);

}
