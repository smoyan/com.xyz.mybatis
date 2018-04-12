/**
 * 
 */

package com.xyz.mybatis.demo.service.impl;

import java.util.List;

import com.xyz.mybatis.core.annotation.Transactional;
import com.xyz.mybatis.core.session.SessionFactoryBean;
import com.xyz.mybatis.demo.dao.AddressDao;
import com.xyz.mybatis.demo.dao.User2Dao;
import com.xyz.mybatis.demo.dao.UserDao;
import com.xyz.mybatis.demo.service.UserService;
import com.xyz.mybatis.model.User;

/**
 * @author lee.
 */
public class UserServiceImpl implements UserService {

  private UserDao userDao = SessionFactoryBean.getInstance().getMapper(UserDao.class);
  private AddressDao addressDao = SessionFactoryBean.getInstance().getMapper(AddressDao.class);
  private User2Dao user2Dao = SessionFactoryBean.getInstance().getMapper(User2Dao.class);

  public List<User> select() {
    System.out.println("user2Dao : " + user2Dao);
    return user2Dao.select();
  }

  @Override
  public User load(int id) {
    return userDao.load(id);
  }

  @Override
  @Transactional(isReadOnly = false)
  public boolean update(User user) {
    addressDao.update(null);
    return userDao.update(user);
  }

  @Override
  public List<User> loadAll() {
    return userDao.loadAll();
  }

}
