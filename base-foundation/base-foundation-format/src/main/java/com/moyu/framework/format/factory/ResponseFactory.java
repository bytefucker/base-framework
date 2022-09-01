package com.moyu.framework.format.factory;

import com.moyu.framework.core.response.ResponseResult;
import com.moyu.framework.core.response.Result;
import org.springframework.util.Assert;

/**
 * ResponseFactory
 *
 * @author yihongzhi
 * @date 2022/8/22
 */
public class ResponseFactory {

  public static final ResponseFactory FACTORY = new ResponseFactory();

  public <T> Result<T> build(Integer code, String msg, T data) {
    Assert.notNull(code, "code can't be null");
    return new ResponseResult<>(code, msg, data);
  }

  public <T> Result<T> build(Integer code, T data) {
    return build(code, "", data);
  }

  public <T> Result<T> buildSuccess() {
    return build(0, "", null);
  }

  public <T> Result<T> buildSuccess(T data) {
    return build(0, "", data);
  }
}
