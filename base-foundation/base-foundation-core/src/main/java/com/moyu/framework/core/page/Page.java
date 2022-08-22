package com.moyu.framework.core.page;

import java.util.List;

/**
 * 分页结果接口
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public interface Page<T> {

  /**
   * 分页条数
   *
   * @return
   */
  Long getTotals();

  /**
   * 分页记录
   *
   * @return
   */
  List<T> getRecords();
}
