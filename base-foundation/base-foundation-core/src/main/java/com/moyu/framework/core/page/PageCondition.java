package com.moyu.framework.core.page;

/**
 * PageRequest
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public class PageCondition<T> implements Pageable {

  private Integer pageNo;
  private Integer pageSize;
  private T condition;

  @Override
  public Integer getPageNo() {
    if (pageNo == null || pageNo <= 0) {
      pageNo = 1;
    }
    return pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  @Override
  public Integer getPageSize() {
    if (pageSize == null || pageSize <= 0) {
      pageSize = 10;
    }
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public T getCondition() {
    return condition;
  }

  public void setCondition(T condition) {
    this.condition = condition;
  }
}
