package org.luojs.aqs_test;

import java.util.concurrent.CyclicBarrier;

/**
 * @author 罗俊生
 * @date 2021/3/29 15:14
 */
public class TestMutex {

    private static CyclicBarrier barrier = new CyclicBarrier(31);
    private static int a = 0;
    private static Mutex mutex = new Mutex();

    public static void main(String[] args) throws Exception {
        //说明:我们启用30个线程，每个线程对i自加10000次，同步正常的话，最终结果应为300000；
        //未加锁前
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < 30; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        //没有同步措施的a++；
                        increment1();
                    }
                    try {
                        //等30个线程累加完毕
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        barrier.await();
        long endTime1 = System.currentTimeMillis();
        System.out.println("加锁前，a=" + a);
        System.out.println("耗时：" + (endTime1 - startTime1));

        //加锁后，重置CyclicBarrier
        barrier.reset();
        a = 0;
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        //a++采用Mutex进行同步处理
                        increment2();
                    }
                    try {
                        //等30个线程累加完毕
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        barrier.await();
        long endTime2 = System.currentTimeMillis();
        System.out.println("加锁后，a=" + a);
        System.out.println("耗时：" + (endTime2 - startTime2));
    }

    /**
     * 没有同步措施的a++
     */
    public static void increment1() {
        a++;
    }

    /**
     * 使用自定义的Mutex进行同步处理的a++
     */
    public static void increment2() {
        mutex.lock();
        a++;
        mutex.unlock();
    }
}
