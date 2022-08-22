package com.moyu.framework.core.page;

/**
 * PageAble
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public interface Pageable {

  Integer getPageNo();

  Integer getPageSize();

  String getOrderBy();

}
