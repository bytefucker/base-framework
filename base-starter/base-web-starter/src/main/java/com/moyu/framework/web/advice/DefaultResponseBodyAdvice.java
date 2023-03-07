package com.moyu.framework.web.advice;

import com.moyu.framework.core.response.DefaultResponse;
import com.moyu.framework.core.response.Response;
import java.lang.reflect.Method;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * DefaultResponseBodyAdvice
 *
 * @author byte_fucker
 * @date 2022/10/26
 */
@ControllerAdvice(annotations = RestController.class)
public class DefaultResponseBodyAdvice implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter returnType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    Method method = returnType.getMethod();
    return method != null && method.getAnnotation(DisableWrapper.class) == null;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType,
      MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request, ServerHttpResponse response) {
    if (body instanceof Response) {
      return body;
    }
    return new DefaultResponse<>(0, "", body);
  }
}
