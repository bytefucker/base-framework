package com.moyu.framework.web.errors;

import com.moyu.framework.exception.BaseException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 * 覆盖springboot默认的错误消息
 *
 * @author yhz
 * @date 2023/3/29
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultErrorAttributes extends
    org.springframework.boot.web.servlet.error.DefaultErrorAttributes {


  @Override
  public Map<String, Object> getErrorAttributes(WebRequest webRequest,
      ErrorAttributeOptions options) {
    Map<String, Object> errorAttributes = getErrorAttributes(webRequest,
        options.isIncluded(Include.STACK_TRACE));
    if (!options.isIncluded(Include.STACK_TRACE)) {
      errorAttributes.remove("trace");
    }
    if (!options.isIncluded(Include.BINDING_ERRORS)) {
      errorAttributes.remove("errors");
    }
    return errorAttributes;
  }

  private Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
    Map<String, Object> errorAttributes = new LinkedHashMap<>();
    errorAttributes.put("timestamp", new Date());
    addPath(errorAttributes, webRequest);
    addErrorDetails(errorAttributes, webRequest, includeStackTrace);
    return errorAttributes;
  }

  private void addErrorDetails(Map<String, Object> errorAttributes, WebRequest webRequest,
      boolean includeStackTrace) {
    Throwable error = getError(webRequest);
    if (error != null) {
      while (error instanceof ServletException && error.getCause() != null) {
        error = error.getCause();
      }
      errorAttributes.put("exception", error.getClass().getName());
      if (includeStackTrace) {
        addStackTrace(errorAttributes, error);
      }
      if (error instanceof BaseException) {
        BaseException ex = (BaseException) error;
        errorAttributes.put("code", ex.getCode());
        if (ex.getDebug() != null) {
          errorAttributes.put("debug", ex.getDebug());
        }
        if (ex.getArgs() != null) {
          errorAttributes.put("args", ex.getArgs());
        }
      } else {
        errorAttributes.put("code", 0);
      }
    }
    addErrorMessage(errorAttributes, webRequest, error);
  }

  private void addErrorMessage(Map<String, Object> errorAttributes, WebRequest webRequest,
      Throwable error) {
    BindingResult result = extractBindingResult(error);
    if (result == null) {
      addExceptionErrorMessage(errorAttributes, webRequest, error);
    } else {
      addBindingResultErrorMessage(errorAttributes, result);
    }
  }

  private void addExceptionErrorMessage(Map<String, Object> errorAttributes, WebRequest webRequest,
      Throwable error) {
    errorAttributes.put("message", getMessage(webRequest, error));
  }

  private void addBindingResultErrorMessage(Map<String, Object> errorAttributes,
      BindingResult result) {
    errorAttributes.put("message", "Validation failed for object='" + result.getObjectName() + "'. "
        + "Error count: " + result.getErrorCount());
    errorAttributes.put("errors", result.getAllErrors());
  }

  private BindingResult extractBindingResult(Throwable error) {
    if (error instanceof BindingResult) {
      return (BindingResult) error;
    }
    return null;
  }

  private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
    StringWriter stackTrace = new StringWriter();
    error.printStackTrace(new PrintWriter(stackTrace));
    stackTrace.flush();
    errorAttributes.put("trace", stackTrace.toString());
  }

  private void addPath(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
    String path = getAttribute(requestAttributes, RequestDispatcher.ERROR_REQUEST_URI);
    if (path != null) {
      errorAttributes.put("path", path);
    }
  }

  private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
    return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
  }
}
