package com.moyu.framework.core.reponse;

import com.moyu.framework.core.page.PageResult;
import java.util.List;
import lombok.Data;

/**
 * ListResponse
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
@Data
public class PageResponse<T> implements Response<PageResult<T>> {

  private Integer code;
  private String msg;
  private PageResult<T> data;

  @Override
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  @Override
  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Override
  public PageResult<T> getData() {
    return data;
  }

  public void setData(PageResult<T> data) {
    this.data = data;
  }
}
