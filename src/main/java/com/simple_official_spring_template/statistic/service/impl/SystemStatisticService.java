package com.simple_official_spring_template.statistic.service.impl;

import com.simple_official_spring_template.statistic.nativequery.SystemStatisticNativeQuery;
import com.simple_official_spring_template.statistic.result.TopBestSellerProductResult;
import com.simple_official_spring_template.statistic.result.TopInventoryProductResult;
import com.simple_official_spring_template.statistic.service.ISystemStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.YearMonth;
import java.util.List;

@Service
public class SystemStatisticService implements ISystemStatisticService {

    @Autowired
    private SystemStatisticNativeQuery systemStatisticNativeQuery;


    @Override
    public long getTotalOrder(Timestamp startTime, Timestamp endTime) {
        return systemStatisticNativeQuery.getTotalOrder(startTime, endTime);
    }

    @Override
    public long getTotalExchangeOrReturnOfOrder(Timestamp startTime, Timestamp endTime) {
        return systemStatisticNativeQuery.getTotalExchangeOrReturnOfOrder(startTime, endTime);
    }

    @Override
    public List<TopBestSellerProductResult> getTopBestSellerProduct(Timestamp startTime, Timestamp endTime, String orderBy, Integer limit) {
        return systemStatisticNativeQuery.getTopBestSellerProduct(startTime, endTime, orderBy, limit);
    }

    @Override
    public List<TopInventoryProductResult> getTopInventoryProduct(Integer limit) {
        return systemStatisticNativeQuery.getTopInventoryProduct(limit);
    }

    @Override
    public int getNumberOfDaysByMonth(int year, int month) {
        return YearMonth.of(year, month).lengthOfMonth();
    }

}
