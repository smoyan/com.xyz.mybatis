/**
 * 
 */

package com.xyz.mybatis.core.session;

import org.apache.ibatis.session.SqlSession;

/**
 * @author lee.
 */
public class ConnectionHolder {

  private boolean isDirty;

  private SqlSession session;

  public ConnectionHolder() {
  }

  public ConnectionHolder(SqlSession session) {
    this.session = session;
  }

  public boolean isDirty() {
    return isDirty;
  }

  public void setDirty(boolean isDirty) {
    this.isDirty = isDirty;
  }

  public SqlSession getSession() {
    System.out.println("session : " + session);
    return session;
  }

  public void setSession(SqlSession session) {
    this.session = session;
  }

}
