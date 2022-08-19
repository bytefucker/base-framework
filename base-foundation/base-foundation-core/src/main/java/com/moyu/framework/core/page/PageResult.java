package com.moyu.framework.core.page;

import java.util.List;
import lombok.Data;

/**
 * PageResult
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
@Data
public class PageResult<T> {

  private long totals;
  private List<T> records;

  public Long getTotals() {
    return totals;
  }

  public void setTotals(Long totals) {
    this.totals = totals;
  }

  public List<T> getRecords() {
    return records;
  }

  public void setRecords(List<T> records) {
    this.records = records;
  }
}
