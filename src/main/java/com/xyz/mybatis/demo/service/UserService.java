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

  public List<User> load();

  public int update(User user);

}
