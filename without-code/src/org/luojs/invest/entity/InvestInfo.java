package org.luojs.invest.entity;

/**
 * 投资信息
 */
public class InvestInfo {

    private Double moneyAmount;

    private Integer dayAmount;

    private Double baseMoney;

    private Double remainMoney;

    public InvestInfo() {
    }

    public InvestInfo(Double moneyAmount, Integer dayAmount) {
        this.moneyAmount = moneyAmount;
        this.dayAmount = dayAmount;
        this.remainMoney = moneyAmount;
        if (dayAmount==0) {
            baseMoney = moneyAmount;
        } else {
            baseMoney = moneyAmount / dayAmount;
        }
    }

    public double buyStock(double money) {
        if (this.remainMoney < money) {
            money = this.remainMoney;
        }
        this.remainMoney -= money;
        return money;
    }

    public void redeemStock(double money) {
        this.remainMoney += money;
    }

    public Double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Integer getDayAmount() {
        return dayAmount;
    }

    public void setDayAmount(Integer dayAmount) {
        this.dayAmount = dayAmount;
    }

    public Double getBaseMoney() {
        return baseMoney;
    }

    public void setBaseMoney(Double baseMoney) {
        this.baseMoney = baseMoney;
    }

    public Double getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(Double remainMoney) {
        this.remainMoney = remainMoney;
    }

    @Override
    public String toString() {
        return "InvestInfo{" +
                "moneyAmount=" + moneyAmount +
                ", dayAmount=" + dayAmount +
                ", baseMoney=" + baseMoney +
                ", remainMoney=" + remainMoney +
                '}';
    }
}
