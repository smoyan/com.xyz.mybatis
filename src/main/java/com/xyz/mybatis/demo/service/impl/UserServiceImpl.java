/**
 * 
 */

package com.xyz.mybatis.demo.service.impl;

import com.xyz.mybatis.core.base.Service;
import com.xyz.mybatis.core.annotation.Transactional;
import com.xyz.mybatis.core.session.SessionFactoryBean;
import com.xyz.mybatis.demo.dao.AddressDao;
import com.xyz.mybatis.demo.dao.UserDao;
import com.xyz.mybatis.demo.service.UserService;
import com.xyz.mybatis.model.User;

import java.util.List;

/**
 * @author lee.
 */
public class UserServiceImpl extends Service implements UserService {

  private UserDao userDao;
  private AddressDao addressDao;

  private UserDao getUserDao() {
    if (userDao == null || !getConnectionHolder().isDirty())
      userDao = SessionFactoryBean.getMapper(UserDao.class, getConnectionHolder().getSession());
    return userDao;
  }

  private AddressDao getAddressDao() {
    if (userDao == null || !getConnectionHolder().isDirty())
      addressDao = SessionFactoryBean.getMapper(AddressDao.class, getConnectionHolder().getSession());
    return addressDao;
  }

  @Override
  public List<User> load() {
    return getUserDao().load();
  }

  @Override
  @Transactional(isReadOnly = false)
  public int update(User user) {
    getAddressDao().update(null);
    getUserDao().update(user);
    return 0;
  }

}
