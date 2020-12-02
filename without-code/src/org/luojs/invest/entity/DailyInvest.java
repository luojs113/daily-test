package org.luojs.invest.entity;

import java.util.List;

/**
 * @author 罗俊生
 * @date 2020/9/25 11:58
 */
public class DailyInvest {

    private int budget;

    private String stockName;

    private String stockKey;

    private List<Double> rangeList;

    public DailyInvest() {
    }

    public DailyInvest(int budget, String stockName, String stockKey, List<Double> rangeList) {
        this.budget = budget;
        this.stockName = stockName;
        this.stockKey = stockKey;
        this.rangeList = rangeList;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockKey() {
        return stockKey;
    }

    public void setStockKey(String stockKey) {
        this.stockKey = stockKey;
    }

    public List<Double> getRangeList() {
        return rangeList;
    }

    public void setRangeList(List<Double> rangeList) {
        this.rangeList = rangeList;
    }
}
