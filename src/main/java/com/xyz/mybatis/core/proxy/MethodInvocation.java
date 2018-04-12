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

  private static final ThreadLocal<TransactionInfo> _transactionInfo = new ThreadLocal<>();

  public MethodInvocation(T target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Object result = null;
    TransactionInfo transactionInfo = null;
    logger.info("before : " + method.getName());
    try {
      Transactional transactional = AnnotationUtils.getTransactionalAnnotation(target, method);
      boolean autoCommit = true;
      if (transactional != null) {
        autoCommit = false;
      }
      transactionInfo = createTransactionInfoIfNecessary(autoCommit);

      result = method.invoke(target, args);
    } catch (Throwable err) {
      if (transactionInfo != null) {
        transactionInfo.setException(true);

        if (!transactionInfo.hasTransaction()) {
          transactionInfo.getConnectionHolder().getSession().rollback();
        }
      }
      throw err;
    } finally {
      if (transactionInfo != null && !transactionInfo.hasTransaction()) {
        transactionInfo.getConnectionHolder().getSession().commit();
        transactionInfo.getConnectionHolder().close();
        SessionFactoryBean.getInstance().getSessionLocal().remove();
        _transactionInfo.remove();
      }
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public T getProxy() {
    return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
  }

  TransactionInfo createTransactionInfoIfNecessary(boolean autoCommit) {
    TransactionInfo transactionInfo = new TransactionInfo();

    TransactionInfo old = _transactionInfo.get();
    if (old != null) {
      transactionInfo.setConnectionHolder(old.getConnectionHolder());
    } else {
      ConnectionHolder connectionHolder = SessionFactoryBean.getInstance().create(autoCommit);
      SessionFactoryBean.getInstance().getSessionLocal().set(connectionHolder);
      transactionInfo.setConnectionHolder(connectionHolder);
    }
    transactionInfo.setOldTransactionInfo(old);
    _transactionInfo.set(transactionInfo);
    return transactionInfo;
  }

  static class TransactionInfo {
    private TransactionInfo oldTransactionInfo;
    private ConnectionHolder connectionHolder;
    private boolean exception;

    public TransactionInfo getOldTransactionInfo() {
      return oldTransactionInfo;
    }

    public ConnectionHolder getConnectionHolder() {
      return connectionHolder;
    }

    public void setOldTransactionInfo(TransactionInfo oldTransactionInfo) {
      this.oldTransactionInfo = oldTransactionInfo;
    }

    public void setConnectionHolder(ConnectionHolder connectionHolder) {
      this.connectionHolder = connectionHolder;
    }

    public boolean hasTransaction() {
      return this.oldTransactionInfo != null;
    }

    public boolean isException() {
      return exception;
    }

    public void setException(boolean exception) {
      this.exception = exception;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("TransactionInfo [oldTransactionInfo=");
      builder.append(oldTransactionInfo);
      builder.append(", connectionHolder=");
      builder.append(connectionHolder);
      builder.append(", exception=");
      builder.append(exception);
      builder.append("]");
      return builder.toString();
    }

  }

}