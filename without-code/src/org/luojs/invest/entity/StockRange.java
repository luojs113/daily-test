package org.luojs.invest.entity;

public class StockRange {

    private Integer stockId;

    private Double value;

    private Double dayRange;

    public StockRange() {
    }

    public StockRange(Double value, Double dayRange) {
        this.value = value;
        this.dayRange = dayRange;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getDayRange() {
        return dayRange;
    }

    public void setDayRange(Double dayRange) {
        this.dayRange = dayRange;
    }

    @Override
    public String toString() {
        return "StockRange{" +
                "stockId=" + stockId +
                ", value=" + value +
                ", dayRange=" + dayRange +
                '}';
    }
}
