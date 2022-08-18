package com.moyu.framework.event.listener.executor;

import com.moyu.framework.event.listener.Callback;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:
 * @create: 2020-06-17 17:55
 **/
@Slf4j
public class SingleThreadEventListenerExecutor implements EventListenerExecutor {

  @Override
  public void execute(Runnable runnable, Callback callback) {
    try {
      runnable.run();
      callback.success();
    } catch (Exception e) {
      callback.fail(e);
    }
  }

  @Override
  public void execute(Runnable runnable) {
    try {
      runnable.run();
    } catch (Exception e) {
      log.error("执行失败", e);
    }
  }
}
