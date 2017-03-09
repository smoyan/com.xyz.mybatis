/**
 * 
 */

package com.xyz.mybatis.core.session;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author lee
 *
 */
public class SqlSessionProxy<T> {
  private SqlSession sqlSession;
  private Class<T> mapperInterface;
  private SqlSessionFactory sqlSessionFactory;

  /**
   * 
   */
  public SqlSessionProxy(SqlSessionFactory sqlSessionFactory, Class<T> mapperInterface) {
    this.sqlSessionFactory = sqlSessionFactory;
    this.mapperInterface = mapperInterface;
  }

  public T getObject() {
    if (sqlSession == null) {
      synchronized (mapperInterface) {
        if (sqlSession == null) {
          this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
        }
      }
    }
    return this.sqlSession.getMapper(mapperInterface);
  }

}
