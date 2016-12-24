/**
 * 
 */

package com.xyz.mybatis;

import java.util.List;

import org.junit.Test;

import com.xyz.mybatis.core.proxy.ProxyFactory;
import com.xyz.mybatis.demo.service.UserService;
import com.xyz.mybatis.demo.service.impl.UserServiceImpl;
import com.xyz.mybatis.model.Address;
import com.xyz.mybatis.model.User;

/**
 * @author lee
 */
public class ClientDemo {
  UserService userService = ProxyFactory.createProxy(UserServiceImpl.class);

  @Test
  public void testLoad() {
    int id = 1;
    User user = userService.load(id);

    System.out.println(user.getUsername());
    List<Address> addresses = user.getAddresses();
    for (Address item : addresses) {
      System.out.println(item.getAddress() + "\t" + item.getId());
    }

  }

  @Test
  public void test2() {
    userService.select();
  }
}
