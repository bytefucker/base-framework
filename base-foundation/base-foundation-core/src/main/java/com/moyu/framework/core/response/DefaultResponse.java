package com.moyu.framework.core.response;

import lombok.Data;

/**
 * DefaultResponse
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
@Data
public class DefaultResponse<T> implements Response<T> {

  private Integer code;
  private String msg;
  private T data;

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
  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
