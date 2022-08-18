package com.moyu.framework.event.listener.consumer;

import com.moyu.framework.event.core.DomainEvent;
import com.moyu.framework.event.core.DomainEventBase;
import com.moyu.framework.event.listener.BatchDomainEventListener;
import com.moyu.framework.event.listener.Callback;
import com.moyu.framework.event.listener.executor.EventListenerExecutor;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author
 * @date 2020-10-09 11:02
 **/
public class BatchEventConsumer extends AbstractEventConsumer {

  private final BatchDomainEventListener<DomainEventBase<?>> batchDomainEventListener;
  private final EventListenerExecutor eventListenerExecutor;

  public BatchEventConsumer(BatchDomainEventListener<DomainEventBase<?>> batchDomainEventListener,
      EventListenerExecutor eventListenerExecutor,
      DomainEvent domainEvent,
      Class<DomainEventBase<?>> domainEventClass) {
    super(domainEvent, domainEventClass);
    this.batchDomainEventListener = batchDomainEventListener;
    this.eventListenerExecutor = eventListenerExecutor;
  }

  @Override
  protected CompletableFuture<Optional<Exception>> innerExecute(List<DomainEventBase<?>> events) {
    Callback callback = new Callback(1);
    eventListenerExecutor.execute(() -> batchDomainEventListener.onEvent(events), callback);
    return callback.future;
  }
}
