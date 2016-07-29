/**
 * 
 */

package com.xyz.mybatis.core.datasource;

import org.apache.commons.dbcp2.BasicDataSource;

import java.lang.reflect.Field;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * @author lee.
 */
public class DataSourceFactory implements org.apache.ibatis.datasource.DataSourceFactory {
  private BasicDataSource dataSource;

  public DataSourceFactory() {
    dataSource = new BasicDataSource();
  }

  @Override
  public void setProperties(Properties props) {
    Field fields[] = dataSource.getClass().getDeclaredFields();
    try {
      for (Field item : fields) {
        item.setAccessible(true);
        if (props.containsKey(item.getName())) {
          item.set(dataSource, props.get(item.getName()));
        }
      }
    } catch (IllegalArgumentException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  @Override
  public DataSource getDataSource() {
    return dataSource;
  }

}
