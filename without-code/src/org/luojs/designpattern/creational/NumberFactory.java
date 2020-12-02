package org.luojs.designpattern.creational;

/**
 * @author 罗俊生
 * @date 2020/4/30 11:48
 */
public interface NumberFactory {

    static NumberFactory impl = new NumberNumberFactoryImpl();

    Number parse(String s);

    static NumberFactory getFactory() {
        return impl;
    }
}
