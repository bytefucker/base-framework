package com.moyu.framework.message.source;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public abstract class RemoteMessage {

  protected final Set<String> sections = new HashSet<>();

  public void addSections(Collection<String> sectionSet) {
    sections.addAll(sectionSet);
  }

  public void clear() {

  }

  protected abstract String resolveCode(String code, Locale locale);
}
