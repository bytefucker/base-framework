package com.moyu.framework.mybatis.page;

import com.moyu.framework.core.page.Page;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PageResult
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> implements Page<T> {

  private long totals;

  private List<T> records;

  @Override
  public Long getTotals() {
    return totals;
  }

  public void setTotals(Long totals) {
    this.totals = totals;
  }

  @Override
  public List<T> getRecords() {
    return records;
  }

  public void setRecords(List<T> records) {
    this.records = records;
  }

  /**
   * 转换分页类型
   *
   * @param function
   * @param <R>
   * @return
   */
  public <R> PageResult<R> map(Function<T, R> function) {
    return new PageResult<>(
        this.getTotals(),
        this.getRecords().stream().map(function).collect(Collectors.toList())
    );
  }
}
