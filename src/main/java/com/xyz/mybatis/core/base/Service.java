/**
 * 
 */

package com.xyz.mybatis.core.base;

import com.xyz.mybatis.core.session.ConnectionHolder;
import com.xyz.mybatis.core.session.SessionFactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee.
 */
public class Service {

  private ThreadLocal<Map<String, ConnectionHolder>> sessionLocal = new ThreadLocal<>();

  public ConnectionHolder getConnectionHolder() {
    return getConnectionHolder(true);
  }

  public ConnectionHolder getConnectionHolder(boolean autoCommit) {
    String key = this.toString();
    Map<String, ConnectionHolder> map = sessionLocal.get();
    if (map == null) {
      map = new HashMap<>();
      sessionLocal.set(map);
    }
    ConnectionHolder sessionHolder = map.get(key);
    if (sessionHolder == null || sessionHolder.isDirty()) {
      sessionHolder = new ConnectionHolder(SessionFactoryBean.getConnection(autoCommit));
      map.put(key, sessionHolder);
    }
    return sessionHolder;
  }

  public void clearSession() {
    Map<String, ConnectionHolder> map = sessionLocal.get();
    if (map != null) {
      if (map.containsKey(this.toString())) {
        map.remove(this.toString());
      }
    }
  }

}
