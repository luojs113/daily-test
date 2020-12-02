package org.luojs.invest.simulate;

import org.luojs.invest.entity.InvestInfo;
import org.luojs.invest.factory.IStockTrendDao;
import org.luojs.invest.factory.impl.StockTrendDaoImpl;
import org.luojs.invest.strategy.AutomaticInvest;

import java.util.ArrayList;
import java.util.List;

public class simulateTest {

    public static void main(String[] args) {
        InvestInfo investInfo = new InvestInfo(100000d, 250);
        IStockTrendDao stockTrendDao = new StockTrendDaoImpl();
        List<Double> rangeList = stockTrendDao.getRangeList("sz.cybz");
        if (rangeList == null || rangeList.size() == 0) {
            System.out.println("无股票市场交易数据");
            return;
        }

        double stockMoney = 0d;
        List<Double> tradeList = new ArrayList<>();
        List<Double> remainList = new ArrayList<>();
        List<Double> stockList = new ArrayList<>();
        List<Double> totalList = new ArrayList<>();

        for (int i = 0; i < rangeList.size(); i++) {
            double range = rangeList.get(i);
            //1.计算当日盈亏
            stockMoney = stockMoney * (1 + range / 100);
            //2.计算当日投入或赎回
            double trade = AutomaticInvest.planD(range, 20, 3) * investInfo.getBaseMoney();
            //3.根据5天线调整交易
            double fiveRange = range + (i > 0 ? rangeList.get(i - 1) : 0) + (i > 1 ? rangeList.get(i - 2) : 0)
                    + (i > 2 ? rangeList.get(i - 3) : 0) + (i > 3 ? rangeList.get(i - 4) : 0);
            if (fiveRange > 5) {
                trade -= 0.1 * (fiveRange - 5);
            }
            if (fiveRange < -5) {
                trade += 0.1 * (fiveRange + 5);
            }
            //double trade = investInfo.getBaseMoney();

            if (trade == 0) {
                tradeList.add(0d);
                remainList.add(investInfo.getRemainMoney());
                stockList.add(stockMoney);
                totalList.add(investInfo.getRemainMoney() + stockMoney);
                continue;
            }
            //4.进行投入
            if (trade > 0) {
                double realTrade = investInfo.buyStock(trade);
                stockMoney += realTrade;
                tradeList.add(realTrade);
                remainList.add(investInfo.getRemainMoney());
                stockList.add(stockMoney);
                totalList.add(investInfo.getRemainMoney() + stockMoney);
                continue;
            }
            //5.进行赎回
            if (trade < 0) {
                double realTrade = (stockMoney + trade > 0) ? trade : stockMoney;
                investInfo.redeemStock(-realTrade);
                stockMoney += realTrade;
                tradeList.add(realTrade);
                remainList.add(investInfo.getRemainMoney());
                stockList.add(stockMoney);
                totalList.add(investInfo.getRemainMoney() + stockMoney);
                continue;
            }
        }

        System.out.println(investInfo);
        System.out.println("交易天数：" + rangeList.size());
        System.out.println("股票账户：" + stockMoney);
        double totalMoney = investInfo.getRemainMoney() + stockMoney;
        double surplus = totalMoney - investInfo.getMoneyAmount();
        System.out.println("总资金：" + totalMoney);
        System.out.println("总盈亏：" + surplus);
        System.out.println("交易记录：" + tradeList.toString());
        /*System.out.println("剩余资金记录：" + remainList.toString());
        System.out.println("股票资金记录：" + stockList.toString());
        System.out.println("总资金记录：" + totalList.toString());*/
    }
}
