package com.moyu.framework.event.listener.consumer;

import com.moyu.framework.event.core.DomainEventBase;
import com.moyu.framework.event.listener.BatchDomainEventListener;
import com.moyu.framework.event.listener.DomainEventListener;
import com.moyu.framework.event.listener.EventListener;
import com.moyu.framework.event.listener.OrderedDomainEventListener;

/**
 * @author
 * @date 2020-10-09 11:49
 */
public interface EventConsumerFactory {

  @Deprecated
  EventConsumer create(EventListener eventListener);

  EventConsumer create(DomainEventListener<DomainEventBase<?>> listener);

  EventConsumer create(BatchDomainEventListener<DomainEventBase<?>> listener);

  EventConsumer create(OrderedDomainEventListener<DomainEventBase<?>> listener);

}
