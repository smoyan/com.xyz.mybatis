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

  /**
   * 
   */
  public SqlSessionProxy(SqlSessionFactory sqlSessionFactory, Class<T> mapperInterface) {
    setSqlSessionFactory(sqlSessionFactory, mapperInterface);
  }

  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory, Class<T> mapperInterface) {
    this.mapperInterface = mapperInterface;
    if (sqlSession == null) {
      this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
    }
  }

  public T getObject() {
    return this.sqlSession.getMapper(mapperInterface);
  }

}
