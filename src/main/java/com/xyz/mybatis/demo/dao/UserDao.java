/**
 * 
 */

package com.xyz.mybatis.demo.dao;


import com.xyz.mybatis.model.User;

import java.util.List;

/**
 * @author lee.
 */
public interface UserDao {

  public List<User> load();

  public int update(User user);
}
