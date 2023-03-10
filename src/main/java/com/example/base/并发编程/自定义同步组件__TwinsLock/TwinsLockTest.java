package com.example.base.并发编程.自定义同步组件__TwinsLock;

import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * @author jiwei.xue
 * @date 2020/10/30 10:32
 */
public class TwinsLockTest {

    @Test
    public void test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {

            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
