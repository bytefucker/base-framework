package com.moyu.framework.event.publisher;

import com.moyu.framework.event.core.DomainEvent;
import com.moyu.framework.event.core.DomainEventBase;
import com.moyu.framework.event.exception.MissingAnnotationException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author
 * @date 2020-05-08 16:16
 **/
public abstract class DomainEventPublisherBase<T extends DomainEventBase<?>>
    implements DomainEventPublisher<T> {

  private final ConcurrentHashMap<Class<?>, String> topicMapping = new ConcurrentHashMap<>();

  protected void validate(T t) {
    if (t == null) {
      throw new IllegalArgumentException("事件对象不可为空");
    }
  }

  protected String getTopic(T t) {
    String topic = topicMapping.get(t.getClass());
    if (topic != null) {
      return topic;
    }
    DomainEvent domainEvent = t.getClass().getAnnotation(DomainEvent.class);
    if (domainEvent == null) {
      throw new MissingAnnotationException("事件类定义需要添加DomainEvent注解");
    }
    topic = domainEvent.topic();
    topicMapping.put(t.getClass(), topic);
    return topic;
  }

}
