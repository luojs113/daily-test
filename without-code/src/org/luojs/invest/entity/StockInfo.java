package org.luojs.invest.entity;

public class StockInfo {

    private Integer stockId;

    private String stockName;

    private String stockKey;

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
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

    @Override
    public String toString() {
        return "StockInfo{" +
                "stockId=" + stockId +
                ", stockName='" + stockName + '\'' +
                ", stockKey='" + stockKey + '\'' +
                '}';
    }
}
