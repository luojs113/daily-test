package org.luojs.stock_invest.factory;

import java.util.List;

public interface IStockTrendDao {

    List<Double> getRangeList(String stockKey);

    List<Double> getRangeListLimit(String stockKey, int limit);
}
