/**
 * 
 */

package com.xyz.mybatis;

import com.xyz.mybatis.core.proxy.ProxyFactory;
import com.xyz.mybatis.demo.service.UserService;
import com.xyz.mybatis.demo.service.impl.UserServiceImpl;
import com.xyz.mybatis.model.User;

import java.util.List;

/**
 * @author lee.
 */
public class Client {
  UserService userService = ProxyFactory.createProxy(UserServiceImpl.class);

  public static void main(String[] args) throws Exception {
    new Client().test();
    // Thread.sleep(Long.MAX_VALUE);
  }

  public void test() {
    for (int i = 0; i < 3; i++) {
      doIt(i);
    }

  }

  private void doIt(int i) {
    userService.update(null);
    System.out.println("第" + i + "次执行完毕");
    List<User> users = userService.load();
    System.out.println("age: " + users.get(0).getAge());
  }

}
