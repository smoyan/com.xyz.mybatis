/**
 * 
 */

package com.xyz.mybatis.core.session;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lee.
 */
public class ConnectionHolder {
  private static final Logger logger = LoggerFactory.getLogger(ConnectionHolder.class);

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
    logger.info("session : " + session);
    return session;
  }

  public void setSession(SqlSession session) {
    this.session = session;
  }

}
