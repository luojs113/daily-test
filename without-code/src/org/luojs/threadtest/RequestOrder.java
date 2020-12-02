package org.luojs.threadtest;

import java.util.concurrent.Callable;

public class RequestOrder implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        OrderNoCreator creator = OrderNoCreator.getUniqueInstance();
        Integer orderNo = creator.nextOrderNo();
        //System.out.println(orderNo);
        return orderNo;
    }
}
