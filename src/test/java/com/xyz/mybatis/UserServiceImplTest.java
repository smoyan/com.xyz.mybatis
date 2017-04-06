/**
 * 
 */

package com.xyz.mybatis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xyz.mybatis.core.proxy.ProxyFactory;
import com.xyz.mybatis.demo.service.UserService;
import com.xyz.mybatis.demo.service.impl.UserServiceImpl;
import com.xyz.mybatis.model.User;

/**
 * @author lee.
 */
public class UserServiceImplTest {
  private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);
  UserService userService = ProxyFactory.getProxy(UserServiceImpl.class);

  @Test
  public void test() throws InterruptedException {
    long start = System.currentTimeMillis();
    int threadNum = 2;

    final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
    final CyclicBarrier barrier = new CyclicBarrier(threadNum);

    for (int i = 0; i < threadNum; i++) {
      new TaskThread(countDownLatch, i, barrier).start();
    }

    countDownLatch.await();
    System.out.println("耗时(ms):" + (System.currentTimeMillis() - start));
    System.out.println("现在开始休眠");
    Thread.sleep(Integer.MAX_VALUE);
  }

  private final class TaskThread extends Thread {
    private CountDownLatch countDownLatch;
    private int num;
    private CyclicBarrier barrier;

    public TaskThread(CountDownLatch countDownLatch, int num, CyclicBarrier barrier) {
      this.countDownLatch = countDownLatch;
      this.num = num;
      this.barrier = barrier;
    }

    @Override
    public void run() {
      try {
        logger.info(getName() + " 已准备好!正在等待!");
        barrier.await();
        doRun();
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        countDownLatch.countDown();
      }
    }

    /**
     * 
     */
    private void doRun() throws Exception {
      logger.info(getName() + "开始执行");
      User user = null;
      for (int i = 0; i < 10; i++) {
        int id = 3;
        user = userService.load(id);
        user.setAge(user.getAge() + 1);
        userService.update(user);
        user = userService.load(id);
      }
      logger.info(getName() + "第" + num + "次执行完毕, user : " + user);
    }
  }

}
