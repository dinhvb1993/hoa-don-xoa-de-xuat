package com.simple_official_spring_template.statistic.service;

import java.sql.Timestamp;

public interface IUserStatisticService {
    int getTotalOrder(String customerId, Timestamp startTime, Timestamp endTime);
    float getTotalPrice(String customerId, Timestamp startTime, Timestamp endTime);
    int getTotalExchangeOrReturnOrder(String customerId, Timestamp startTime, Timestamp endTime);
}
