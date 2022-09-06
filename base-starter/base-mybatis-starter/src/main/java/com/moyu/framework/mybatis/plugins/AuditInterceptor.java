package com.moyu.framework.mybatis.plugins;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

/**
 * 审计插件
 *
 * @author yihongzhi
 * @date 2022/9/2
 */
public class AuditInterceptor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    return null;
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }
}
