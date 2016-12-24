/**
 * 
 */

package com.xyz.mybatis.demo.service;

import com.xyz.mybatis.model.User;

import java.util.List;

/**
 * @author lee.
 */
public interface UserService {

  public User load(int id);

  public List<User> loadAll();

  public int update(User user);

  public List<User> select();

}
