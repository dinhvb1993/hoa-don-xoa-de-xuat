package com.simple_official_spring_template.utils.service;

public interface IUtilsGeneralService {
    Integer updateOrder(Integer orderId, Boolean isPayment, Boolean isStaticPrice);
}
