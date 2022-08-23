package com.moyu.framework.mybatis.exception;

import com.moyu.framework.exception.BaseException;

/**
 * DBOperationException
 *
 * @author yihongzhi
 * @date 2022/8/23
 */
public class DBOperationException extends BaseException {

  public DBOperationException(String message) {
    super(message);
  }
}
