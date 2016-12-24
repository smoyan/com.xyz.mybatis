package com.xyz.mybatis.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.xyz.mybatis.core.annotation.AnnotationUtils;
import com.xyz.mybatis.core.annotation.Transactional;
import com.xyz.mybatis.core.session.ConnectionHolder;
import com.xyz.mybatis.core.session.SessionFactoryBean;

public class MethodInvocation<T> implements InvocationHandler {

  private T target;
  private ConnectionHolder conHolder;
  private boolean isInTransactional;

  public MethodInvocation(T target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    beforeInvoke(method);
    Object result = method.invoke(target, args);
    afterInvoke(method);
    return result;
  }

  /**
   * @throws SecurityException
   * @throws NoSuchMethodException
   * 
   */
  private void beforeInvoke(Method method) throws NoSuchMethodException, SecurityException {
    System.out.println("before method : " + method.getName());
    Transactional transactional = AnnotationUtils.getTransactionalAnnotation(target, method);
    boolean isAutoCommit = true;
    if (transactional != null) {
      isAutoCommit = false;
      isInTransactional = true;
    }
    conHolder = SessionFactoryBean.getSessionLocal(isAutoCommit).get();
  }

  /**
   * 
   */
  private void afterInvoke(Method method) {
    System.out.println("after method : " + method.getName());
    if (isInTransactional) {
      System.out.println("commit transaction ");
      conHolder.getSession().commit(true);
      conHolder.setDirty(true);
      conHolder.getSession().close();
      SessionFactoryBean.getSessionLocal().remove();
    }
  }

  @SuppressWarnings("unchecked")
  public T getProxy() {
    return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
  }

}