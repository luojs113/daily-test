package org.luojs.threadtest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestManyThread {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<FutureTask<Integer>> taskList = new ArrayList<>();
        Set<Integer> exist = new HashSet<>();
        for (int i = 0; i < 100000; i++) {
            Callable request = new RequestOrder();
            FutureTask<Integer> result = new FutureTask<>(request);
            taskList.add(result);
        }
        for (FutureTask<Integer> result : taskList) {
            new Thread(result).start();
            try {
                exist.add(result.get());
            }catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(exist.size());
        OrderNoCreator creator = OrderNoCreator.getUniqueInstance();
        System.out.println(creator.nextOrderNo()+" : "+(end-start));
    }
}
