package com.moyu.framework.event.listener.consumer;

import com.moyu.framework.event.core.DomainEvent;
import com.moyu.framework.event.core.DomainEventBase;
import com.moyu.framework.event.listener.OrderedDomainEventListener;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author
 * @date 2020-10-09 11:10
 **/
public class OrderedEventConsumer extends AbstractEventConsumer implements EventConsumer {

  private final OrderedDomainEventListener<DomainEventBase<?>> orderedDomainEventListener;

  public OrderedEventConsumer(
      OrderedDomainEventListener<DomainEventBase<?>> orderedDomainEventListener,
      DomainEvent domainEvent,
      Class<DomainEventBase<?>> domainEventClass) {
    super(domainEvent, domainEventClass);
    this.orderedDomainEventListener = orderedDomainEventListener;
  }

  public void executeOne(DomainEventBase<?> event) {
    if (match(event)) {
      orderedDomainEventListener.onEvent(event);
    }
  }

  @Override
  protected CompletableFuture<Optional<Exception>> innerExecute(List<DomainEventBase<?>> events) {
    throw new UnsupportedOperationException();
  }

}
