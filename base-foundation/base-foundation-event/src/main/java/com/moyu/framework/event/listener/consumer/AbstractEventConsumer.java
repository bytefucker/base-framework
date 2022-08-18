package com.moyu.framework.event.listener.consumer;

import com.moyu.framework.event.core.DomainEvent;
import com.moyu.framework.event.core.DomainEventBase;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

/**
 * @author
 * @date 2020-10-09 13:52
 **/
@Slf4j
public abstract class AbstractEventConsumer implements EventConsumer {

  private final DomainEvent domainEvent;
  private final Class<DomainEventBase<?>> domainEventClass;

  public AbstractEventConsumer(DomainEvent domainEvent,
      Class<DomainEventBase<?>> domainEventClass) {
    this.domainEvent = domainEvent;
    this.domainEventClass = domainEventClass;
    log.info("event class: {}", domainEventClass.getName());
  }

  @Override
  public DomainEvent getDomainEvent() {
    return domainEvent;
  }

  @Override
  public CompletableFuture<Optional<Exception>> execute(List<DomainEventBase<?>> events) {
    if (CollectionUtils.isEmpty(events)) {
      return CompletableFuture.completedFuture(Optional.empty());
    }
    events = events.stream()
        .filter(this::match)
        .collect(Collectors.toList());
    if (CollectionUtils.isEmpty(events)) {
      return CompletableFuture.completedFuture(Optional.empty());
    }
    return innerExecute(events);
  }

  protected abstract CompletableFuture<Optional<Exception>> innerExecute(
      List<DomainEventBase<?>> events);

  protected boolean match(DomainEventBase<?> e) {
    return domainEventClass.isAssignableFrom(e.getClass());
  }
}
