package com.moyu.framework.core.reponse;

import java.util.List;
import lombok.Data;

/**
 * ListResponse
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
@Data
public class ListResponse<T> implements Response<List<T>> {

  private Integer code;
  private String msg;
  private List<T> data;

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
  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }
}
