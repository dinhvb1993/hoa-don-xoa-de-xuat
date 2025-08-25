package com.simple_official_spring_template.utils.service.impl;


import com.simple_official_spring_template.converter.ItemOfOrderConverter;
import com.simple_official_spring_template.converter.ShippingFeeConverter;
import com.simple_official_spring_template.dto.ShippingFeeDTO;
import com.simple_official_spring_template.entity.*;
import com.simple_official_spring_template.repository.*;
import com.simple_official_spring_template.utils.Constant;

import com.simple_official_spring_template.utils.service.IUtilsGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.List;

import static com.simple_official_spring_template.utils.enums.Status.*;

@Service
public class UtilsGeneralService implements IUtilsGeneralService {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IItemOfOrderRepository itemOfOrderRepository;

    @Autowired
    private IPromotionRepository promotionRepository;

    @Autowired
    private IUserAddressRepository userAddressRepository;

    @Autowired
    private IShippingFeeRepository shippingFeeRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ShippingFeeConverter shippingFeeConverter;

    @Autowired
    private ItemOfOrderConverter itemOfOrderConverter;

    @Autowired
    private IStatusRepository statusRepository;

    @Autowired
    private IOrderStatusRepository orderStatusRepository;

    @Override
    public Integer updateOrder(Integer orderId, Boolean isPayment, Boolean isStaticPrice) {
        // find current order
        OrderEntity orderEntity = orderRepository.findOneById(orderId);

        Double totalWeight = 0.0;
        Double totalPrice = 0.0;
        Integer errorCode = 0;

        // update ItemOfOrder
        List<ItemOfOrderEntity> itemOfOrderEntities = itemOfOrderRepository.findByOrderId(orderId);

        if (itemOfOrderEntities.size() > 0) {
            orderEntity.setIsAvailable(true);
        } else {
            orderEntity.setIsAvailable(false);
        }

        for (ItemOfOrderEntity itemOfOrderEntity : itemOfOrderEntities) {
            errorCode = 0;
            Double currentPrice = null;
            ProductEntity productEntity = itemOfOrderEntity.getProduct();

            currentPrice = productEntity.getPrice();
            if (itemOfOrderEntity.getQuantity() <=0 || itemOfOrderEntity.getQuantity() > productEntity.getQuantity()) {
                // TODO: update ERROR for ORDER
                orderEntity.setIsAvailable(false);
                errorCode = Constant.ERROR.ERROR_NUMBER.getValue();
            } else {
                itemOfOrderEntity.setPriceOfItem(currentPrice);
                itemOfOrderEntity.setFinalPrice(itemOfOrderEntity.getQuantity() * itemOfOrderEntity.getPriceOfItem());

                // validate promotion code
                if (!StringUtils.isEmpty(itemOfOrderEntity.getPromotionCode())) {
                    PromotionEntity promotionEntity = productEntity.getPromotion();
                    if (promotionEntity != null) {
                        if (!itemOfOrderEntity.getPromotionCode().equals(promotionEntity.getCode())) {
                            errorCode = Constant.ERROR.ERROR_PROMOTION.getValue();
                        } else {
                            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                            if (currentTimestamp.before(promotionEntity.getValidityPeriod())) {

                                if (promotionEntity.getRemainingNumber() == 0) {
                                    return Constant.ERROR.ERROR_PROMOTION.getValue();
                                }

                                Integer applyingNumber = null;
                                if (itemOfOrderEntity.getQuantity() < promotionEntity.getRemainingNumber()) {
                                    applyingNumber = itemOfOrderEntity.getQuantity();
                                } else {
                                    applyingNumber = promotionEntity.getRemainingNumber();
                                }
                                itemOfOrderEntity.setApplyingNumber(applyingNumber);


                                if (isPayment) {
                                    // update remainingNumber for promotion
                                    promotionEntity.setRemainingNumber(promotionEntity.getRemainingNumber() - applyingNumber);
                                    promotionRepository.save(promotionEntity);
                                }


                                // count deduction
                                Double deduction = 0.0;
                                if (promotionEntity.getDiscountPercent() != null) {
                                    deduction += applyingNumber * productEntity.getPrice() * promotionEntity.getDiscountPercent() / 100;
                                }
                                if (promotionEntity.getDiscountMoney() != null) {
                                    deduction += applyingNumber * promotionEntity.getDiscountMoney();
                                }
                                itemOfOrderEntity.setApplyingNumber(applyingNumber);

                                // deduction
                                itemOfOrderEntity.setDeduction(deduction);
                                itemOfOrderEntity.setFinalPrice(itemOfOrderEntity.getFinalPrice() - deduction);

                            } else {
                                errorCode = Constant.ERROR.ERROR_PROMOTION.getValue();
                            }
                        }

                    } else {
                        errorCode = Constant.ERROR.ERROR_PROMOTION.getValue();
                    }


                } else {
                    itemOfOrderEntity.setDeduction(0.0);
                }


                if (errorCode != 0) {
                    itemOfOrderEntity.setDeduction(0.0);
                }


                if (isPayment) {
                    productEntity.setQuantity(productEntity.getQuantity() - itemOfOrderEntity.getQuantity());
                    productRepository.save(productEntity);
                }

                totalPrice += itemOfOrderEntity.getFinalPrice();

                if (productEntity.getWeight() != null){
                    totalWeight += itemOfOrderEntity.getQuantity() * productEntity.getWeight();
                }

            }

            itemOfOrderEntity.setErrorCode(errorCode);

            // ORDER
            // update totalWeight and totalWeight for Order
            orderEntity.setTotalPrice(totalPrice);
            orderEntity.setTotalWeight(totalWeight);

//            // find UserAddress
//            UserAddressEntity userAddressEntity = userAddressRepository.findByUserId(orderEntity.getUser().getId()).get(0);

            // Shipping fee
//            Boolean internalCity = true;
//            if (userAddressEntity.getWard().getDistrict().getCity().getId() != INTERNAL_CITY_ID) {
//                internalCity = false;
//            }
//            orderEntity.setShippingFee(countShippingFee(orderEntity.getTotalWeight(), internalCity));


            orderEntity.setShippingFee(0.0);
            orderEntity.setFinalPrice(orderEntity.getTotalPrice() + orderEntity.getShippingFee());

            if (errorCode != 0) {
                orderEntity.setIsAvailable(false);
            }
            orderRepository.save(orderEntity);


            itemOfOrderRepository.save(itemOfOrderEntity);
        }

        return errorCode;
    }

    public Float countShippingFee(Float currentWeight, Boolean internalCity) {
        ShippingFeeEntity shippingFeeEntity = shippingFeeRepository.findOneByMinWeightLessThanEqualAndMaxWeightGreaterThanAndInternalCity(
                currentWeight,
                currentWeight,
                internalCity
        );
        ShippingFeeDTO resultShippingFee = shippingFeeConverter.toDTO(shippingFeeEntity);

        resultShippingFee.setCurrentWeight(currentWeight);

        Float totalPrice = resultShippingFee.getPrice();

        if (shippingFeeEntity.getAdditionalPrice() > 0) {
            totalPrice += (float) Math.ceil(resultShippingFee.getCurrentWeight() - resultShippingFee.getMinWeight()) * resultShippingFee.getAdditionalPrice();
        }

        return totalPrice;
    }


}
