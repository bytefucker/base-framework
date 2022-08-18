package com.moyu.framework.event.listener.executor;

import com.github.rholder.retry.RetryException;
import com.moyu.framework.event.config.EventConfig;
import com.moyu.framework.event.listener.Callback;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认事件任务执行器
 *
 * @author
 */
@Slf4j
public class DefaultEventListenerExecutor extends AbstractEventListenerExecutor implements
    EventListenerExecutor {

  private final AtomicLong sequenceGenerator = new AtomicLong(0L);
  private final ThreadPoolExecutor executorService;

  public DefaultEventListenerExecutor(EventConfig eventConfig) {
    super(eventConfig.getAttempt(), eventConfig.getMultiplier(), eventConfig.getMaximumTime());
    executorService = new ThreadPoolExecutor(
        eventConfig.getCorePoolSize(),
        eventConfig.getMaximumPoolSize(),
        eventConfig.getKeepAliveTime(),
        TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(eventConfig.getBlockingQueueSize()), r -> {
      Thread thread = new Thread(r, "event-listener-executor-" + sequenceGenerator.get());
      thread.setDaemon(true);
      return thread;
    });
    executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
  }

  @Override
  public void execute(Runnable runnable, Callback callback) {
    executorService.execute(() -> {
      try {
        retryer.call(Executors.callable(runnable));
        callback.success();
      } catch (ExecutionException e) {
        log.error("执行失败", e);
        callback.fail(e);
      } catch (RetryException e) {
        log.error("重试失败", e);
        callback.fail(e);
      }
    });
  }

  @Override
  public void execute(Runnable runnable) {
    executorService.execute(() -> {
      try {
        retryer.call(Executors.callable(runnable));
      } catch (ExecutionException e) {
        log.error("执行失败", e);
      } catch (RetryException e) {
        log.error("重试失败", e);
      }
    });
  }
}
