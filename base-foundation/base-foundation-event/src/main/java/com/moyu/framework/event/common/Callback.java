package com.moyu.framework.event.common;

/**
 * @param <T>
 */
@FunctionalInterface
public interface Callback<T> {

  void call(T t, Exception e);

}
