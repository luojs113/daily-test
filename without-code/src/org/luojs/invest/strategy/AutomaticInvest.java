package org.luojs.invest.strategy;

import java.util.*;

/**
 * 定投策略
 */
public class AutomaticInvest {

    /**
     * 定投策略A
     *
     * @param range 涨跌幅（百分之）
     * @return
     */
    public static double planA(double range) {
        if (range == 0) {
            return 1d;
        }
        if (range > 0) {
            range = Math.min(9d, range);
            double angle = range / (6 * Math.PI);
            double basePix = (1 - Math.tan(angle));
            return Math.max(-9d, basePix);
        }
        if (range < 0) {
            range = Math.max(-6d, range);
            double angle = range / (4 * Math.PI);
            double basePix = (1 - Math.tan(angle));
            return Math.min(9d, basePix);
        }
        return 0d;
    }

    /**
     * 定投策略B
     *
     * @param range 涨跌幅（百分之）
     * @return
     */
    public static double planB(double range) {
        if (range == 0) {
            return 0.8d;
        }
        if (range > 0) {
            range = Math.min(10d, range);
            double angle = range / (8 * Math.PI);
            double basePix = (0.8 - Math.tan(angle));
            return Math.max(-9d, basePix);
        }
        if (range < 0) {
            double basePix = Math.pow(2, -range / 3);
            return Math.min(9d, basePix);
        }
        return 0d;
    }

    /**
     * 定投策略C
     *
     * @param range 涨跌幅（百分之）
     * @return
     */
    public static double planC(double range) {
        if (range == 0) {
            return 1d;
        }
        if (range > 0) {
            range = Math.min(9.42d, range);
            double angle = range / (6 * Math.PI);
            double basePix = (1 - Math.tan(angle));
            return Math.max(-100d, basePix);
        }
        if (range < 0) {
            range = Math.max(-4.7d, range);
            double angle = range / (3 * Math.PI);
            double basePix = (1 - Math.tan(angle));
            return Math.min(100d, basePix);
        }
        return 0d;
    }

    /**
     * 定投策略D，终极版
     * @param range 涨跌幅（百分之）
     * @param pixie 收涨策略基数：
     *              适合京东方A，pixie=5.74
     *              波动较大，建议pixie=6-8
     *              长期看好，建议pixie=10-20
     *              期待爆发，且资金充裕，建议pixie>30
     * @param bristol 收跌策略基数：
     *                 通用，建议bristol=3
     *                 期待爆发，且资金充裕，建议bristol=2.5-2.8
     * @return
     */
    public static double planD(double range, double pixie, double bristol) {
        if (range == 0) {
            return 1d;
        }
        if (range > 0) {
            double basePix = 2 - Math.pow(2, range / pixie);
            return Math.max(-9d, basePix);
        }
        if (range < 0) {
            double basePix = Math.pow(2, -range / bristol);
            return Math.min(9d, basePix);
        }
        return 0d;
    }

}
