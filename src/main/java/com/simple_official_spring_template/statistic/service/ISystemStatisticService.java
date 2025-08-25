package com.simple_official_spring_template.statistic.service;

import com.simple_official_spring_template.statistic.result.TopBestSellerProductResult;
import com.simple_official_spring_template.statistic.result.TopInventoryProductResult;

import java.sql.Timestamp;
import java.util.List;

public interface ISystemStatisticService {
    long getTotalOrder(Timestamp startTime, Timestamp endTime);
    long getTotalExchangeOrReturnOfOrder(Timestamp startTime, Timestamp endTime);
    List<TopBestSellerProductResult> getTopBestSellerProduct(Timestamp startTime, Timestamp endTime, String orderBy, Integer limit);
    List<TopInventoryProductResult> getTopInventoryProduct(Integer limit);
    int getNumberOfDaysByMonth(int year, int month);
}
