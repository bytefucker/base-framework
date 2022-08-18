package com.moyu.framework.event.listener.manager;

import com.moyu.framework.event.core.DomainEvent;
import com.moyu.framework.event.listener.EventListener;
import com.moyu.framework.event.listener.executor.EventListenerExecutor;
import com.moyu.framework.event.metadata.DefaultMetadataReader;
import com.moyu.framework.event.metadata.MetadataReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author:
 * @create: 2020-05-22 15:22
 **/
abstract class AbstractEventListenerManager implements EventListenerManager {

  MetadataReader metadataReader = new DefaultMetadataReader();
  @Qualifier("eventListenerExecutor")
  @Autowired
  EventListenerExecutor eventListenerExecutor;

  protected String getTopic(EventListener listener) {
    DomainEvent domainEvent = metadataReader.getDomainEvent(listener);
    if (domainEvent == null) {
      return null;
    }
    return domainEvent.topic();
  }
}
