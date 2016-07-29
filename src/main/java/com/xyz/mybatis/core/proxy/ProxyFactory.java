/**
 * 
 */

package com.xyz.mybatis.core.proxy;

/**
 * @author lee.
 */
public class ProxyFactory {

  private ProxyFactory() {
  }

  public static <T> T createProxy(Class<T> type) {
    T target = null;
    try {
      target = type.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    MethodInvocation<T> proxy = new MethodInvocation<T>(target);
    return proxy.getProxy();
  }

}
