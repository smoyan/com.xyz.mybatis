<<<<<<< HEAD
/**
 * 
 */

package com.xyz.mybatis.demo.service.impl;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xyz.mybatis.core.annotation.Transactional;
import com.xyz.mybatis.core.proxy.ProxyFactory;
import com.xyz.mybatis.core.session.SessionFactoryBean;
import com.xyz.mybatis.demo.dao.AddressDao;
import com.xyz.mybatis.demo.dao.User2Dao;
import com.xyz.mybatis.demo.dao.UserDao;
import com.xyz.mybatis.demo.service.AddressService;
import com.xyz.mybatis.demo.service.UserService;
import com.xyz.mybatis.model.User;

/**
 * @author lee.
 */
public class UserServiceImpl implements UserService {
  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
  private UserDao userDao = SessionFactoryBean.getInstance().getMapper(UserDao.class);
  private AddressDao addressDao = SessionFactoryBean.getInstance().getMapper(AddressDao.class);
  private User2Dao user2Dao = SessionFactoryBean.getInstance().getMapper(User2Dao.class);
  private AddressService addressService = ProxyFactory.createProxy(AddressServiceImpl.class);

  @Transactional
  @Override
  public List<User> select() {
    return user2Dao.select();
  }

  @Override
  public User load(int id) {
    return userDao.load(id);
  }

  @Override
  @Transactional(isReadOnly = false)
  public int update(User user) {
    addressService.load();
    return userDao.update(user);
  }

  @Override
  public List<User> loadAll() {
    return userDao.loadAll();
  }

}
=======
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

  private UserDao userDao = SessionFactoryBean.getMapper(UserDao.class);
  private AddressDao addressDao = SessionFactoryBean.getMapper(AddressDao.class);
  private User2Dao user2Dao = SessionFactoryBean.getMapper(User2Dao.class);

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
>>>>>>> branch 'master' of https://github.com/leeyazhou/com.xyz.mybatis.git
