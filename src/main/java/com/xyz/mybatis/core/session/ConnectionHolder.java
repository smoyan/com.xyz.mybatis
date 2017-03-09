/**
 * 
 */

package com.xyz.mybatis.core.session;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

/**
 * @author lee.
 */
public class ConnectionHolder {

  // private static final Logger logger = LoggerFactory.getLogger(ConnectionHolder.class);

  private boolean isDirty;

  private SqlSession session;

  private int reference;

  private boolean autoCommit;

  public ConnectionHolder() {
  }

  public ConnectionHolder(SqlSession session, boolean autoCommit) {
    this.session = session;
    this.autoCommit = autoCommit;
  }

  public boolean isDirty() {
    return isDirty;
  }

  public void setDirty(boolean isDirty) {
    this.isDirty = isDirty;
  }

  public SqlSession getSession() {
    return session;
  }

  public void setSession(SqlSession session) {
    this.session = session;
  }

  public void release() throws SQLException {
    reference--;
    // logger.info("release : " + reference);
  }

  public SqlSession acquired() throws SQLException {
    reference++;
    return session;
  }

  public void close() {
    if (reference == 0) {
      session.close();
    }
  }

  /**
   * @return the autoCommit
   */
  public boolean isAutoCommit() {
    return autoCommit;
  }

  /**
   * @param autoCommit the autoCommit to set
   */
  public void setAutoCommit(boolean autoCommit) {
    this.autoCommit = autoCommit;
  }

}
