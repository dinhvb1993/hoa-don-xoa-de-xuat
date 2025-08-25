package com.simple_official_spring_template.statistic.api;

import com.simple_official_spring_template.statistic.result.TopBestSellerProductResult;
import com.simple_official_spring_template.statistic.result.TopInventoryProductResult;
import com.simple_official_spring_template.statistic.service.ISystemStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class SystemStatisticAPI {

    @Autowired
    ISystemStatisticService systemStatisticService;

    @GetMapping(value = "api-system-statistic/get-total-order")
    public long getTotalOrder(@RequestParam(value = "startTime") Timestamp startTime, @RequestParam(value = "endTime") Timestamp endTime) {
        return systemStatisticService.getTotalOrder(startTime, endTime);
    }

    @GetMapping(value = "api-system-statistic/get-total-exchange-or-return-of-order")
    public long getTotalExchangeOrReturnOfOrder(@RequestParam(value = "startTime") Timestamp startTime, @RequestParam(value = "endTime") Timestamp endTime) {
        return systemStatisticService.getTotalExchangeOrReturnOfOrder(startTime, endTime);
    }

    @GetMapping(value = "api-system-statistic/get-top-best-seller-product")
    public List<TopBestSellerProductResult> getTopBestSellerProduct(@RequestParam(value = "startTime") Timestamp startTime, @RequestParam(value = "endTime") Timestamp endTime, @RequestParam(value = "orderBy") String orderBy, @RequestParam(value = "limit") Integer limit) {
        return systemStatisticService.getTopBestSellerProduct(startTime, endTime, orderBy, limit);
    }

    @GetMapping(value = "api-system-statistic/get-top-inventory-product")
    public List<TopInventoryProductResult> getTopInventoryProduct(@RequestParam(value = "limit") Integer limit) {
        return systemStatisticService.getTopInventoryProduct(limit);
    }

    @GetMapping(value = "api-system-statistic/get-number-of-days-by-month")
    public float getNumberOfDaysByMonth(@RequestParam(value = "year") int year, @RequestParam(value = "month") int month) {
        return systemStatisticService.getNumberOfDaysByMonth(year, month);
    }
}
