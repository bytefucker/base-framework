package com.moyu.framework.utils;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * ServletUtil
 *
 * @author yihongzhi
 * @date 2022/9/8
 */
public final class ServletUtil {

  public static final String AUTHORIZATION = "Authorization";

  private ServletUtil() {
  }

  /**
   * 读取http body
   *
   * @param request
   * @return
   * @throws IOException
   */
  public static byte[] getBody(HttpServletRequest request) throws IOException {
    return request.getInputStream().readAllBytes();
  }


  /**
   * 读取http body
   *
   * @param request
   * @param tClass
   * @param <T>
   * @return
   * @throws IOException
   */
  public static <T> T getBody(HttpServletRequest request, Class<T> tClass) throws IOException {
    return JsonUtil.toBean(getBody(request), tClass);
  }


  /**
   * 读取请求header token
   *
   * @param request
   * @return
   */
  public String getAuthorization(HttpServletRequest request) {
    String authorization = request.getHeader(AUTHORIZATION);
    if (StringUtils.isEmpty(authorization)) {
      return null;
    }
    return authorization.contains("Bearer") ? authorization.substring(7).trim()
        : authorization.trim();
  }


}
