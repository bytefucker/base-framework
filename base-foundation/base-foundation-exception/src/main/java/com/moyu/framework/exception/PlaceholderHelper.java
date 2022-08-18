package com.moyu.framework.exception;

import java.util.Map;
import java.util.Objects;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * 占位符替换工具类
 */
public class PlaceholderHelper extends PropertyPlaceholderHelper {

  /**
   * 默认实例化常用占位串
   */
  public static final PlaceholderHelper INSTANCE;

  static {
    INSTANCE = new PlaceholderHelper("{", "}");
  }

  /**
   * Creates a new {@code PlaceholderUtil} that uses the supplied prefix and suffix. 无法解决的占位符将被忽略
   *
   * @param placeholderPrefix the prefix that denotes the start of a placeholder
   * @param placeholderSuffix the suffix that denotes the end of a placeholder
   */
  public PlaceholderHelper(String placeholderPrefix, String placeholderSuffix) {
    this(placeholderPrefix, placeholderSuffix, null, true);
  }

  /**
   * Creates a new {@code PlaceholderUtil} that uses the supplied prefix and suffix.
   *
   * @param placeholderPrefix              the prefix that denotes the start of a placeholder
   * @param placeholderSuffix              the suffix that denotes the end of a placeholder
   * @param valueSeparator                 the separating character between the placeholder variable
   *                                       and the associated default value, if any(占位符变量和其默认值的分隔符号，如
   *                                       ${name : lilei}),":"即该值
   * @param ignoreUnresolvablePlaceholders indicates whether unresolvable placeholders should be
   *                                       ignored ({@code true}) or cause an exception ({@code
   *                                       false})
   */
  public PlaceholderHelper(String placeholderPrefix, String placeholderSuffix,
      String valueSeparator, boolean ignoreUnresolvablePlaceholders) {
    super(placeholderPrefix, placeholderSuffix, valueSeparator, ignoreUnresolvablePlaceholders);
  }

  /**
   * 增加支持map方式引入
   *
   * @param value 待填充字符串
   * @param map   值空间
   * @return 占位后字符串
   */
  public String replacePlaceholders(String value, final Map<String, String> map) {
    org.springframework.util.Assert.notNull(map, "'map' must not be null");
    return replacePlaceholders(value, map::get);
  }

  /**
   * 增加支持array方式引入,此时需要保证占位符为顺序号
   *
   * @param value   待填充字符串
   * @param objects 值空间
   * @return 占位后字符串
   */
  public String replacePlaceholders(String value, final Object[] objects) {
    org.springframework.util.Assert.notNull(objects, "'objects' must not be null");
    return replacePlaceholders(value, placeholderName -> {
      if (Integer.parseInt(placeholderName) < objects.length) {
        Object obj = objects[Integer.parseInt(placeholderName)];
        return Objects.isNull(obj) ? null : obj.toString();
      } else {
        return null;
      }
    });
  }
}
