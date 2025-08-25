package com.simple_official_spring_template.statistic.service.impl;

import com.simple_official_spring_template.statistic.nativequery.UserStatisticNativeQuery;
import com.simple_official_spring_template.statistic.service.IUserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserStatisticService implements IUserStatisticService {

    @Autowired
    private UserStatisticNativeQuery userStatisticNativeQuery;

    @Override
    public int getTotalOrder(String customerId, Timestamp startTime, Timestamp endTime) {
        return userStatisticNativeQuery.getTotalOrder(customerId, startTime, endTime);
    }

    @Override
    public float getTotalPrice(String customerId, Timestamp startTime, Timestamp endTime) {
        return userStatisticNativeQuery.getTotalPrice(customerId, startTime, endTime);
    }

    @Override
    public int getTotalExchangeOrReturnOrder(String customerId, Timestamp startTime, Timestamp endTime) {
        return userStatisticNativeQuery.getTotalExchangeOrReturnOrder(customerId, startTime, endTime);
    }
}
