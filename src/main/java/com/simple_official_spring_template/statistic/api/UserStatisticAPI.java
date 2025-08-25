package com.simple_official_spring_template.statistic.api;

import com.simple_official_spring_template.statistic.service.IUserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class UserStatisticAPI {

    @Autowired
    IUserStatisticService userStatisticService;

    @GetMapping(value = "api-user-statistic/get-total-order")
    public int getTotalOrder(@RequestParam(value = "userId") String userId, @RequestParam(value = "startTime") Timestamp startTime, @RequestParam(value = "endTime") Timestamp endTime) {
        return userStatisticService.getTotalOrder(userId, startTime, endTime);
    }

    @GetMapping(value = "api-user-statistic/get-total-price")
    public float getTotalPrice(@RequestParam(value = "userId") String userId, @RequestParam(value = "startTime") Timestamp startTime, @RequestParam(value = "endTime") Timestamp endTime) {
        return userStatisticService.getTotalPrice(userId, startTime, endTime);
    }

    @GetMapping(value = "api-user-statistic/get-total-exchange-or-return-order")
    public float getTotalReturnOrExchangeOrder(@RequestParam(value = "userId") String userId, @RequestParam(value = "startTime") Timestamp startTime, @RequestParam(value = "endTime") Timestamp endTime) {
        return userStatisticService.getTotalExchangeOrReturnOrder(userId, startTime, endTime);
    }
}
