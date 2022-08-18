package com.moyu.framework.event.listener.manager;

import com.moyu.framework.event.core.DomainEventBase;
import com.moyu.framework.event.listener.DomainEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2020-05-22 15:17
 **/
@Component("singleEventListenerManager")
public class SingleEventListenerManager extends AbstractEventListenerManager implements
    EventListenerManager {

  @Autowired(required = false)
  private List<DomainEventListener<DomainEventBase<?>>> domainEventListeners;
  private Map<Class<DomainEventBase<?>>, List<DomainEventListener<DomainEventBase<?>>>> listenerMapping;

  //@PostConstruct
  public void initListenerMapping() {
    if (domainEventListeners == null || domainEventListeners.isEmpty()) {
      return;
    }

    listenerMapping = this.domainEventListeners.stream()
        .collect(Collectors.groupingBy(metadataReader::getDomainEventClass));
  }

  @Override
  public List<String> getTopics() {
    if (domainEventListeners == null) {
      return new ArrayList<>();
    }
    return this.domainEventListeners.stream()
        .map(this::getTopic)
        .distinct()
        .collect(Collectors.toList());
  }

  @Override
  public void execute(List<? extends DomainEventBase<?>> events) {
    if (listenerMapping == null || listenerMapping.isEmpty()) {
      return;
    }

    events.forEach(event -> {
      List<DomainEventListener<DomainEventBase<?>>> listeners = listenerMapping
          .get(event.getClass());
      if (listeners == null) {
        return;
      }

      listeners.forEach(listener -> eventListenerExecutor.execute(() -> {
        listener.onEvent(event);
      }));
    });
  }

}
