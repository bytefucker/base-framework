package com.moyu.framework.event.listener;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public final class Callback {

  public final CompletableFuture<Optional<Exception>> future = new CompletableFuture<>();
  private final int total;
  private final AtomicInteger success = new AtomicInteger(0);
  private final AtomicInteger fail = new AtomicInteger(0);

  public void success() {
    if (total == success.incrementAndGet()) {
      log.debug("finish total:{} success:{} fail:{}", total, success.get(), fail.get());
      future.complete(Optional.empty());
    }

  }

  public void fail(Exception exception) {
    log.debug(exception.getMessage() + " error occured by writing data");
    fail.incrementAndGet();
    future.complete(Optional.of(exception));
  }


}
