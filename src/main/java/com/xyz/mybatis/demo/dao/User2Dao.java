/**
 * 
 */

package com.xyz.mybatis.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.xyz.mybatis.core.annotation.Repository;
import com.xyz.mybatis.model.User;

/**
 * @author lee
 */
@Repository
public interface User2Dao {

  @SelectProvider(type = User2DaoProvider.class, method = "select")
  public List<User> select();
}
