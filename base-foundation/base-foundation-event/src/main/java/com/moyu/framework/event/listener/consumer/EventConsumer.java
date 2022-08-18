package com.moyu.framework.event.listener.consumer;

import com.moyu.framework.event.core.DomainEvent;
import com.moyu.framework.event.core.DomainEventBase;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author
 * @date 2020-10-09 11:09
 */
public interface EventConsumer {

  CompletableFuture<Optional<Exception>> execute(List<DomainEventBase<?>> events);

  DomainEvent getDomainEvent();
}
