package com.moyu.framework.event.listener.consumer;


import com.moyu.framework.event.config.EventConfig;
import com.moyu.framework.event.core.DomainEvent;
import com.moyu.framework.event.core.DomainEventBase;
import com.moyu.framework.event.listener.BatchDomainEventListener;
import com.moyu.framework.event.listener.DomainEventListener;
import com.moyu.framework.event.listener.EventListener;
import com.moyu.framework.event.listener.OrderedDomainEventListener;
import com.moyu.framework.event.listener.executor.DefaultEventListenerExecutor;
import com.moyu.framework.event.metadata.DefaultMetadataReader;
import com.moyu.framework.event.metadata.MetadataReader;

/**
 * @author
 * @date 2020-10-09 11:38
 **/
public class DefaultEventConsumerFactory implements EventConsumerFactory {

  private final MetadataReader metadataReader = new DefaultMetadataReader();
  private final EventConfig eventConfig;

  public DefaultEventConsumerFactory(EventConfig eventConfig) {
    this.eventConfig = eventConfig;
  }

  @Override
  public EventConsumer create(BatchDomainEventListener<DomainEventBase<?>> listener) {
    DomainEvent domainEvent = metadataReader.getDomainEvent(listener);
    Class<DomainEventBase<?>> domainEventClass = metadataReader
        .getDomainEventClass(listener.getClass(), BatchDomainEventListener.class);
    return new BatchEventConsumer(listener,
        new DefaultEventListenerExecutor(eventConfig),
        domainEvent,
        domainEventClass);
  }

  @Override
  public EventConsumer create(OrderedDomainEventListener<DomainEventBase<?>> listener) {
    DomainEvent domainEvent = metadataReader.getDomainEvent(listener);
    Class<DomainEventBase<?>> domainEventClass = metadataReader
        .getDomainEventClass(listener.getClass(), OrderedDomainEventListener.class);
    return new OrderedEventConsumer(listener,
        domainEvent,
        domainEventClass);
  }

  @Override
  public EventConsumer create(DomainEventListener<DomainEventBase<?>> listener) {
    DomainEvent domainEvent = metadataReader.getDomainEvent(listener);
    Class<DomainEventBase<?>> domainEventClass = metadataReader
        .getDomainEventClass(listener.getClass(), DomainEventListener.class);
    return new SingleEventConsumer(listener,
        new DefaultEventListenerExecutor(eventConfig),
        domainEvent,
        domainEventClass);
  }

  @Deprecated
  @SuppressWarnings("unchecked")
  public EventConsumer create(EventListener eventListener) {
    DomainEvent domainEvent = metadataReader.getDomainEvent(eventListener);
    if (eventListener instanceof BatchDomainEventListener) {
      BatchDomainEventListener<DomainEventBase<?>> baseBatchDomainEventListener = (BatchDomainEventListener<DomainEventBase<?>>) eventListener;
      Class<DomainEventBase<?>> domainEventClass = metadataReader
          .getDomainEventClass(baseBatchDomainEventListener.getClass(),
              BatchDomainEventListener.class);
      return new BatchEventConsumer(baseBatchDomainEventListener,
          new DefaultEventListenerExecutor(eventConfig),
          domainEvent,
          domainEventClass);
    } else if (eventListener instanceof OrderedDomainEventListener) {
      OrderedDomainEventListener<DomainEventBase<?>> orderedDomainEventListener = (OrderedDomainEventListener<DomainEventBase<?>>) eventListener;
      Class<DomainEventBase<?>> domainEventClass = metadataReader
          .getDomainEventClass(orderedDomainEventListener.getClass(),
              OrderedDomainEventListener.class);
      return new OrderedEventConsumer(orderedDomainEventListener,
          domainEvent, domainEventClass);


    } else {
      DomainEventListener<DomainEventBase<?>> domainEventListener = (DomainEventListener<DomainEventBase<?>>) eventListener;
      Class<DomainEventBase<?>> domainEventClass = metadataReader
          .getDomainEventClass(domainEventListener.getClass(), DomainEventListener.class);
      return new SingleEventConsumer(domainEventListener,
          new DefaultEventListenerExecutor(eventConfig),
          domainEvent,
          domainEventClass);
    }
  }

}
