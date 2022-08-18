package com.moyu.framework.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 编码与解码操作工具类
 */
@Slf4j
public final class CodecUtil {

  public static final String UTF_8 = "UTF-8";

  /**
   * 将 URL 编码
   */
  @SneakyThrows
  public static String encodeURL(String str) {
    return URLEncoder.encode(str, UTF_8);
  }

  /**
   * 将 URL 解码
   */
  @SneakyThrows
  public static String decodeURL(String str) {
    return URLDecoder.decode(str, UTF_8);
  }

}
