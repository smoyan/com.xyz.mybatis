/**
 * 
 */

package com.xyz.mybatis.core.session;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lee.
 */
public class SessionFactoryBean {
  private static SqlSessionFactory sqlSessionFactory;
  private static final String resource = "mybatis.xml";

  public static SqlSessionFactory getSqlSessionFactory() {
    if (sqlSessionFactory == null) {
      synchronized (SessionFactoryBean.class) {
        if (sqlSessionFactory == null)
          initSqlSessionFactory();
      }
    }
    return sqlSessionFactory;
  }

  private static void initSqlSessionFactory() {
    try {
      InputStream inputStream = Resources.getResourceAsStream(resource);
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Configuration getConfiguration() {
    return getSqlSessionFactory().getConfiguration();
  }

  public static <T> T getMapper(Class<T> type, SqlSession session) {
    return getConfiguration().getMapper(type, session);
  }

  public static SqlSession getConnection() {
    return getSqlSessionFactory().openSession();
  }

  public static SqlSession getConnection(boolean autoCommit) {
    return getSqlSessionFactory().openSession(autoCommit);
  }

}
