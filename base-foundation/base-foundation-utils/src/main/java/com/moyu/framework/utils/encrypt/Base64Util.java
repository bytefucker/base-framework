package com.moyu.framework.utils.encrypt;

import java.nio.charset.StandardCharsets;
import lombok.experimental.UtilityClass;
import org.apache.commons.codec.binary.Base64;

/**
 * base64工具
 *
 * @author
 */
@UtilityClass
public class Base64Util {

  /**
   * <p>
   * BASE64字符串解码为二进制数据
   * </p>
   *
   * @param base64 base64字符串
   * @return raw bytes
   */
  public static byte[] decode2Byte(String base64) {
    return new Base64().decode(base64.getBytes());
  }

  /**
   * BASE64字符串解码为字符串
   *
   * @param base64 base64字符串
   * @return raw bytes
   */
  public static String decode(String base64) {
    return new String(Base64.decodeBase64(base64), StandardCharsets.UTF_8);
  }

  /**
   * <p>
   * 二进制数据编码为 标准BASE64字符串
   * </p>
   * https://en.wikipedia.org/wiki/Base64
   *
   * @param bytes raw bytes
   * @return base64字符串
   */
  public static String encodeStd(byte[] bytes) {
    return Base64.encodeBase64String(bytes);
  }

  /**
   * <p>
   * 二进制数据编码为 url safe BASE64字符串
   * </p>
   * https://en.wikipedia.org/wiki/Base64
   *
   * @param bytes raw bytes
   * @return base64字符串
   */
  public static String encodeUrlSafe(byte[] bytes) {
    return Base64.encodeBase64URLSafeString(bytes);
  }

  /**
   * <p>
   * 字符串转换为utf8字节数组再编码为 url safe BASE64字符串
   * </p>
   * https://en.wikipedia.org/wiki/Base64
   *
   * @param str String str
   * @return base64字符串
   */
  public static String encodeStd(String str) {
    return Base64.encodeBase64String(str.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * <p>
   * 字符串转换为utf8字节数组再编码为 url safe BASE64字符串
   * </p>
   * https://en.wikipedia.org/wiki/Base64
   *
   * @param str String str
   * @return base64字符串
   */
  public static String encodeUrlSafe(String str) {
    return Base64.encodeBase64URLSafeString(str.getBytes(StandardCharsets.UTF_8));
  }
}
