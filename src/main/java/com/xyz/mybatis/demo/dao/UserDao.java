/**
 * 
 */

package com.xyz.mybatis.demo.dao;

import java.util.List;

import com.xyz.mybatis.core.annotation.Repository;
import com.xyz.mybatis.core.base.Dao;
import com.xyz.mybatis.model.User;

/**
 * @author lee.
 */
@Repository
public interface UserDao extends Dao<User> {

  public User load(int id);

  public List<User> loadAll();

  public boolean update(User user);
}
