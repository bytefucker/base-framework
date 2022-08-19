package com.moyu.framework.core.response;

/**
 * Reponse
 *
 * @author yihongzhi
 * @date 2022/8/19
 */
public interface Response<T> {

  Integer getCode();

  String getMsg();

  T getData();

}
