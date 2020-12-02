package org.luojs.designpattern.creational;

import java.math.BigDecimal;

/**
 * @author 罗俊生
 * @date 2020/4/30 11:49
 */
public class NumberNumberFactoryImpl implements NumberFactory {

    public Number parse(String s) {
        return new BigDecimal(s);
    }
}
