package com.moyu.framework.format.factory;

import com.moyu.framework.core.response.DefaultResponse;
import com.moyu.framework.core.response.Response;
import org.springframework.util.Assert;

/**
 * ResponseFactory
 *
 * @author yihongzhi
 * @date 2022/8/22
 */
public class ResponseFactory {

  public static final ResponseFactory FACTORY = new ResponseFactory();

  public <T> Response<T> build(Integer code, String msg, T data) {
    Assert.notNull(code, "code can't be null");
    return new DefaultResponse<>(code, msg, data);
  }

  public <T> Response<T> build(Integer code, T data) {
    return build(code, "", data);
  }

  public <T> Response<T> buildSuccess() {
    return build(0, "", null);
  }

  public <T> Response<T> buildSuccess(T data) {
    return build(0, "", data);
  }
}
