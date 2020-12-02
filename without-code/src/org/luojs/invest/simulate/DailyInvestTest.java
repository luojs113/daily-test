package org.luojs.invest.simulate;

import org.luojs.invest.entity.DailyInvest;
import org.luojs.invest.factory.IStockTrendDao;
import org.luojs.invest.factory.impl.StockTrendDaoImpl;
import org.luojs.invest.strategy.AutomaticInvest;

import java.util.*;

/**
 * @author 罗俊生
 * @date 2020/9/25 12:01
 */
public class DailyInvestTest {

    public static void main(String[] args) {
        List<DailyInvest> dailyInvestList =
                getDailyInvestList();
        for (DailyInvest dailyInvest : dailyInvestList) {
            List<Double> rangeList = dailyInvest.getRangeList();
            int todayIndex = rangeList.size() - 1;
            //由公式计算绝对投入
            double trade = AutomaticInvest.planD(rangeList.get(todayIndex), 10, 3);
            //根据5天线调整交易
            double fiveRange = rangeList.get(todayIndex) + rangeList.get(todayIndex - 1) + rangeList.get(todayIndex - 2) + rangeList.get(todayIndex - 3) + rangeList.get(todayIndex - 4);
            if (fiveRange > 5) {
                trade -= 0.1 * (fiveRange - 5);
            }
            if (fiveRange < -5) {
                trade += 0.1 * (fiveRange + 5);
            }
            System.out.println(dailyInvest.getStockName() + " : " + dailyInvest.getBudget() * trade);
        }
    }

    public static List<DailyInvest> getDailyInvestList() {
        List<DailyInvest> dailyInvestList = new ArrayList<>();
        IStockTrendDao stockTrendDao = new StockTrendDaoImpl();

        List<Double> list1 = stockTrendDao.getRangeListLimit("501047", 5);
        DailyInvest invest1 = new DailyInvest(100, "全指证券", "501047", list1);
        dailyInvestList.add(invest1);

        List<Double> list2 = stockTrendDao.getRangeListLimit("320007", 5);
        DailyInvest invest2 = new DailyInvest(100, "诺安成长", "320007", list2);
        dailyInvestList.add(invest2);

        return dailyInvestList;
    }
}
