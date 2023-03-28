package com.moyu.framework.core.convert;

import com.moyu.framework.core.entity.Entity;
import com.moyu.framework.core.vo.VO;

/**
 * 实体,VO转换接口
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public interface Convert<E extends Entity<?>, V extends VO> {


  /**
   * VO转实体
   *
   * @param v
   * @return
   */
  E mapTo(V v);

  /**
   * 实体转VO
   *
   * @param e
   * @return
   */
  V mapTo(E e);

}
