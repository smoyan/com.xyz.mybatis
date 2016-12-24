/**
 * 
 */

package com.xyz.mybatis.core.proxy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lee.
 */
public class ProxyFactory {

  private static final ConcurrentHashMap<String, Object> services = new ConcurrentHashMap<String, Object>();

  private ProxyFactory() {
  }

  @SuppressWarnings("unchecked")
  public static <T> T createProxy(Class<T> type) {
    T target = null;
    try {
      Object existTarget = services.get(type.getName());
      if (existTarget != null) {
        target = (T) existTarget;
      } else {
        target = type.newInstance();
        services.putIfAbsent(type.getName(), target);
      }
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    MethodInvocation<T> proxy = new MethodInvocation<T>(target);
    return proxy.getProxy();
  }

}
