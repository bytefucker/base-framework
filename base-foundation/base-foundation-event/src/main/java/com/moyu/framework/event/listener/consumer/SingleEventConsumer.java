package com.moyu.framework.event.listener.consumer;


import com.moyu.framework.event.core.DomainEvent;
import com.moyu.framework.event.core.DomainEventBase;
import com.moyu.framework.event.listener.Callback;
import com.moyu.framework.event.listener.DomainEventListener;
import com.moyu.framework.event.listener.executor.EventListenerExecutor;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author
 * @date 2020-10-09 11:10
 **/
public class SingleEventConsumer extends AbstractEventConsumer implements EventConsumer {

  private final DomainEventListener<DomainEventBase<?>> domainEventListener;
  private final EventListenerExecutor eventListenerExecutor;

  public SingleEventConsumer(DomainEventListener<DomainEventBase<?>> domainEventListener,
      EventListenerExecutor eventListenerExecutor,
      DomainEvent domainEvent,
      Class<DomainEventBase<?>> domainEventClass) {
    super(domainEvent, domainEventClass);
    this.domainEventListener = domainEventListener;
    this.eventListenerExecutor = eventListenerExecutor;
  }

  @Override
  protected CompletableFuture<Optional<Exception>> innerExecute(List<DomainEventBase<?>> events) {
    final Callback callback = new Callback(events.size());
    for (DomainEventBase<?> event : events) {
      eventListenerExecutor.execute(() -> domainEventListener.onEvent(event), callback);
    }
    return callback.future;
  }

}
