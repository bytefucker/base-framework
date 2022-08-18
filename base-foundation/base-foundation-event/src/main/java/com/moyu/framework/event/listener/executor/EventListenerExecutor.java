package com.moyu.framework.event.listener.executor;

import com.moyu.framework.event.listener.Callback;

/**
 * 事件消费执行器
 *
 * @author
 */
public interface EventListenerExecutor {

  void execute(Runnable runnable, Callback callback);

  void execute(Runnable runnable);

}
