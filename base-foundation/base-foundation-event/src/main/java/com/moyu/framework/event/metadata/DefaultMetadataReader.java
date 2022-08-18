package com.moyu.framework.event.metadata;

import com.moyu.framework.event.core.DomainEvent;
import com.moyu.framework.event.core.DomainEventBase;
import com.moyu.framework.event.listener.BatchDomainEventListener;
import com.moyu.framework.event.listener.DomainEventListener;
import com.moyu.framework.event.listener.EventListener;
import com.moyu.framework.event.listener.OrderedDomainEventListener;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author
 * @date 2020-05-08 20:14
 **/
public class DefaultMetadataReader implements MetadataReader {

  @Override
  public Class<DomainEventBase<?>> getDomainEventClass(DomainEventListener<?> listener) {
    return getDomainEventClass(listener.getClass(), DomainEventListener.class);
  }

  @Override
  public Class<DomainEventBase<?>> getDomainEventClass(BatchDomainEventListener<?> listener) {
    return getDomainEventClass(listener.getClass(), BatchDomainEventListener.class);
  }

  @Override
  public Class<DomainEventBase<?>> getDomainEventClass(OrderedDomainEventListener<?> listener) {
    return getDomainEventClass(listener.getClass(), OrderedDomainEventListener.class);
  }

  @Override
  public Class<DomainEventBase<?>> getDomainEventClass(Class<?> clazz,
      Class<?> domainEventListenerClass) {
    Type[] types = clazz.getGenericInterfaces();
    Optional<? extends Class<DomainEventBase<?>>> domainEventClazz = Arrays.stream(types)
        .filter(type -> type instanceof ParameterizedType)
        .map(type -> {
          ParameterizedType parameterizedType = (ParameterizedType) type;
          if (parameterizedType.getRawType().getTypeName()
              .equals(domainEventListenerClass.getName())) {
            Type[] actualArgumentTypes = parameterizedType.getActualTypeArguments();
            if (actualArgumentTypes.length != 1) {
              return null;
            }
            if (actualArgumentTypes[0] instanceof Class) {
              return (Class<DomainEventBase<?>>) actualArgumentTypes[0];
            }
          }
          return null;
        })
        .filter(Objects::nonNull)
        .findFirst();

    if (domainEventClazz.isPresent()) {
      return domainEventClazz.get();
    } else if (clazz != Object.class) {
      return getDomainEventClass(clazz.getSuperclass(), domainEventListenerClass);
    }
    return null;
  }


  @Override
  public DomainEvent getDomainEvent(DomainEventListener<?> listener) {
    Class<?> clazz = getDomainEventClass(listener);
    if (clazz == null) {
      return null;
    }
    return clazz.getAnnotation(DomainEvent.class);
  }

  @Override
  public DomainEvent getDomainEvent(BatchDomainEventListener<?> listener) {
    Class<?> clazz = getDomainEventClass(listener);
    if (clazz == null) {
      return null;
    }
    return clazz.getAnnotation(DomainEvent.class);
  }

  @Override
  public DomainEvent getDomainEvent(EventListener listener) {
    DomainEvent domainEvent = listener.getClass().getAnnotation(DomainEvent.class);
    if (domainEvent != null) {
      return domainEvent;
    }

    Class<?> clazz = getDomainEventClass(listener.getClass(),
        getEventListenerClass(listener.getClass()));
    if (clazz == null) {
      return null;
    }
    return clazz.getAnnotation(DomainEvent.class);
  }

  private Class<?> getEventListenerClass(Class<?> clazz) {
    for (Class<?> parentInterface : clazz.getInterfaces()) {
      if (parentInterface == EventListener.class) {
        return clazz;
      }
    }

    for (Class<?> parentInterface : clazz.getInterfaces()) {
      if (getEventListenerClass(parentInterface) != null) {
        return parentInterface;
      }
    }

    if (clazz.getSuperclass() != Object.class) {
      return getEventListenerClass(clazz.getSuperclass());
    }

    return null;
  }


}
