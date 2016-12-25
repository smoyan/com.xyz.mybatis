package com.xyz.mybatis.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xyz.mybatis.core.annotation.AnnotationUtils;
import com.xyz.mybatis.core.annotation.Transactional;
import com.xyz.mybatis.core.session.ConnectionHolder;
import com.xyz.mybatis.core.session.SessionFactoryBean;

public class MethodInvocation<T> implements InvocationHandler {
  private static final Logger logger = LoggerFactory.getLogger(MethodInvocation.class);
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
    logger.info("before invoke method : " + method.getName());
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
    if (isInTransactional) {
      conHolder.getSession().commit(true);
      conHolder.setDirty(true);
      conHolder.getSession().close();
      SessionFactoryBean.getSessionLocal().remove();
    }
    logger.info("after invoke method : " + method.getName());
  }

  @SuppressWarnings("unchecked")
  public T getProxy() {
    return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
  }

}