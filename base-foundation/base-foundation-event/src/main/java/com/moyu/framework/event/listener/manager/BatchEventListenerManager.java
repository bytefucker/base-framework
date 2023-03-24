package com.moyu.framework.event.listener.manager;


import com.moyu.framework.event.core.DomainEventBase;
import com.moyu.framework.event.listener.BatchDomainEventListener;
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
@Component("batchEventListenerManager")
public class BatchEventListenerManager extends AbstractEventListenerManager implements
    EventListenerManager {

  @Autowired(required = false)
  private List<BatchDomainEventListener> batchDomainEventListeners;
  private Map<Class<DomainEventBase<?>>, List<BatchDomainEventListener>> batchListenerMapping;

  //@PostConstruct
  public void initListenerMapping() {
    if (batchDomainEventListeners == null || batchDomainEventListeners.isEmpty()) {
      return;
    }

    batchListenerMapping = this.batchDomainEventListeners.stream()
        .collect(Collectors.groupingBy(metadataReader::getDomainEventClass));
  }

  @Override
  public List<String> getTopics() {
    if (batchDomainEventListeners == null) {
      return new ArrayList<>();
    }

    return this.batchDomainEventListeners.stream()
        .map(this::getTopic)
        .distinct()
        .collect(Collectors.toList());
  }

  @Override
  public void execute(List<? extends DomainEventBase<?>> events) {
    if (batchListenerMapping == null || batchListenerMapping.isEmpty()) {
      return;
    }
    Map<Class<?>, List<DomainEventBase<?>>> groupedRecords = events.stream()
        .collect(Collectors.groupingBy(DomainEventBase::getClass));
    groupedRecords.forEach((k, groupedEvents) -> {
      List<BatchDomainEventListener> listeners = batchListenerMapping.get(k);
      if (listeners == null) {
        return;
      }
      listeners.forEach(listener -> eventListenerExecutor.execute(() -> {
        listener.onEvent(groupedEvents);
      }));
    });
  }

}
