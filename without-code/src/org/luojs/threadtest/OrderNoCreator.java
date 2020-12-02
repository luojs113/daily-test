package org.luojs.threadtest;

public class OrderNoCreator {

    private volatile static OrderNoCreator uniqueInstance;

    private Integer orderNo = 0;

    private OrderNoCreator() {
    }

    /**
     * 直接使用静态方法获取实例
     * @return Singleton
     */
    public static OrderNoCreator getUniqueInstance() {
        //先判断对象是否已经实例化，没有实例化才进入加锁代码
        if(uniqueInstance == null) {
            //类对象加锁
            synchronized (OrderNoCreator.class) {
                if(uniqueInstance == null) {
                    uniqueInstance = new OrderNoCreator();
                }
            }
        }
        return uniqueInstance;
    }

    public Integer nextOrderNo(){
        //synchronized (orderNo) {
            orderNo++;
        //}
        return orderNo;
    }
}
