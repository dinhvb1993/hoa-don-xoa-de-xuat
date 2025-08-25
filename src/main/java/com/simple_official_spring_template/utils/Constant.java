package com.simple_official_spring_template.utils;

public class Constant {
    public static final Integer INTERNAL_CITY_ID = 79;
    public static final String SHOP_CART_INIT = "shop_cart_init";
    public static final String SHOP_CART_PROCESSING = "shop_cart_processing";
    public static final String SHOP_CART_SUCCESS = "shop_cart_success";
    public static final String SHOP_CART_FAIL = "shop_cart_fail";


    // ERROR CODE
    public static enum ERROR {
        ERROR_NUMBER(1),
        ERROR_PROMOTION(2);

        Integer value;

        ERROR(Integer value){
            this.value = value;
        }

        public Integer getValue(){
            return this.value;
        }
    }
}
