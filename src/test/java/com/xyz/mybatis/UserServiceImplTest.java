/**
 * 
 */

package com.xyz.mybatis;

import java.util.List;

import org.junit.Test;

import com.xyz.mybatis.core.proxy.ProxyFactory;
import com.xyz.mybatis.demo.service.UserService;
import com.xyz.mybatis.demo.service.impl.UserServiceImpl;
import com.xyz.mybatis.model.User;

/**
 * @author lee.
 */
public class UserServiceImplTest {
  UserService userService = ProxyFactory.createProxy(UserServiceImpl.class);

  @Test
  public void test() throws InterruptedException {
    new Thread() {
      @Override
      public void run() {
        userService.update(null);
        System.out.println("第" + 1 + "次执行完毕");
        List<User> users = userService.loadAll();
        System.out.println("age: " + users.get(0).getAge());
      }
    }.start();

    new Thread() {
      @Override
      public void run() {
        int id = 1;
        User user = userService.load(id);
        user.setAge(user.getAge() + 1);
        userService.update(user);
        System.out.println("第" + 2 + "次执行完毕");
        user = userService.load(id);
        System.out.println("age: " + user.getAge());
      }
    }.start();

    Thread.sleep(Integer.MAX_VALUE);
  }

}
