package com.moyu.framework.event.listener.executor;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * @author
 * @date 2020-06-17 17:56
 **/
@Slf4j
public abstract class AbstractEventListenerExecutor implements EventListenerExecutor {

  private final static int MAX_WAIT_TIME = 5;
  protected Retryer<Object> retryer;

  public AbstractEventListenerExecutor(int attempt, int multiplier, int maximumTime) {
    retryer = RetryerBuilder.newBuilder()
        .retryIfException()
        .withWaitStrategy(WaitStrategies.exponentialWait(multiplier, maximumTime, TimeUnit.MINUTES))
        .withStopStrategy(StopStrategies.stopAfterAttempt(attempt))
        .withRetryListener(new RetryListener() {
          @Override
          public <V> void onRetry(Attempt<V> attempt) {
            //这里attempt代表成功或者失败
            //如果失败的时候 调用getExceptionCause 会导致抛异常
            //所以这里调整根据成功还是失败做对应的处理
            //当重试次数大于1时进行后续重试逻辑，第一次重试其实是第一次调用
            if (attempt.getAttemptNumber() > 1L) {
              if (attempt.hasResult()) {
                log.warn("重试成功:{}", attempt.getResult());
              } else if (attempt.hasException()) {
                log.warn("失败重试", attempt.getExceptionCause());
              } else {
                log.warn("其他:{}", attempt);
              }
            }
          }
        })
        .build();
  }
}
