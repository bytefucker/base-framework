package com.moyu.framework.core.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseResult
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> implements Result<T> {

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
