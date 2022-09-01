package com.moyu.framework.core.response;

/**
 * Result
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public interface Result<T> {

  /**
   * 返回码
   *
   * @return
   */
  Integer getCode();

  /**
   * 返回消息
   *
   * @return
   */
  String getMsg();

  /**
   * 返回数据
   *
   * @return
   */
  T getData();

}
