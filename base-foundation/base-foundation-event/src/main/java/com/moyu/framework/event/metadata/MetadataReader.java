package com.moyu.framework.event.metadata;


import com.moyu.framework.event.core.DomainEvent;
import com.moyu.framework.event.core.DomainEventBase;
import com.moyu.framework.event.listener.BatchDomainEventListener;
import com.moyu.framework.event.listener.DomainEventListener;
import com.moyu.framework.event.listener.EventListener;
import com.moyu.framework.event.listener.OrderedDomainEventListener;

public interface MetadataReader {

  /**
   * @param listener
   * @description 获取listener对应的泛型参数
   * @author caosujie
   * @date 2020-05-08 20:13
   */
  Class<DomainEventBase<?>> getDomainEventClass(DomainEventListener<?> listener);

  /**
   * 获取batch listener对应的泛型参数
   *
   * @param listener
   * @return
   */
  Class<DomainEventBase<?>> getDomainEventClass(BatchDomainEventListener<?> listener);

  /**
   * @param listener
   * @return java.lang.Class<com.cestc.framework.event.sdk.DomainEventBase < ?>>
   * @description 获取listener对应的泛型参数
   * @author caosujie
   * @date 2020-05-08 20:13
   */
  Class<DomainEventBase<?>> getDomainEventClass(OrderedDomainEventListener<?> listener);

  /**
   * 获取domaineventlistener监听的事件的类型
   *
   * @param clazz
   * @param domainEventListenerClass
   * @return java.lang.Class<com.cestc.framework.event.sdk.DomainEventBase < ?>>
   */
  Class<DomainEventBase<?>> getDomainEventClass(Class<?> clazz, Class<?> domainEventListenerClass);

  /**
   * @param listener
   * @return com.cestc.framework.event.sdk.DomainEvent
   * @description 获取listener泛型参数类型的DomainEvent注解
   * @author caosujie
   * @date 2020-05-08 20:13
   */
  DomainEvent getDomainEvent(DomainEventListener<?> listener);

  /**
   * 获取DomainEvent注解
   *
   * @param listener
   * @return
   */
  DomainEvent getDomainEvent(BatchDomainEventListener<?> listener);

  /**
   * 获取DomainEvent注解
   *
   * @param listener
   * @return
   */
  DomainEvent getDomainEvent(EventListener listener);

}
