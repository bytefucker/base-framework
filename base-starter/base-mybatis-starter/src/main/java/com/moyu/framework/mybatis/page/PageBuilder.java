package com.moyu.framework.mybatis.page;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.moyu.framework.core.page.Pageable;

/**
 * 分页构建工具
 *
 * @author yihongzhi
 * @date 2022/8/22
 */
public final class PageBuilder<T> {

  private Pageable pageable;
  private ISelect select;

  public PageBuilder<T> pageable(Pageable pageable) {
    this.pageable = pageable;
    return this;
  }

  public PageBuilder<T> select(ISelect select) {
    this.select = select;
    return this;
  }

  public PageResult<T> build() {
    Page<T> page = PageHelper
        .startPage(pageable.getPageNo(), pageable.getPageSize(), pageable.getOrderBy())
        .doSelectPage(select);
    return new PageResult<>(page.getTotal(), page.getResult());
  }
}
