package com.moyu.framework.event.listener;

import com.moyu.framework.event.core.DomainEventBase;
import com.moyu.framework.event.listener.manager.EventListenerManager;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 事件转发器
 *
 * @author
 */
public abstract class BaseDomainEventDispatcher implements DomainEventDispatcher {

  @Qualifier("singleEventListenerManager")
  @Autowired
  private EventListenerManager singleEventListenerManager;
  @Qualifier("batchEventListenerManager")
  @Autowired
  private EventListenerManager batchEventListenerManager;
  @Qualifier("orderedEventListenerManager")
  @Autowired
  private EventListenerManager orderedEventListenerManager;

  protected volatile boolean running = true;

  protected List<String> getTopics() {
    return Stream.concat(Stream.concat(singleEventListenerManager.getTopics().stream(),
                batchEventListenerManager.getTopics().stream()),
            orderedEventListenerManager.getTopics().stream())
        .distinct()
        .collect(Collectors.toList());
  }

  protected void execute(List<DomainEventBase<?>> records) {
    batchEventListenerManager.execute(records);

    singleEventListenerManager.execute(records);

    orderedEventListenerManager.execute(records);
  }

}
