package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.ItemOfOrderConverter;
import com.simple_official_spring_template.converter.ShippingFeeConverter;
import com.simple_official_spring_template.dto.ItemOfOrderDTO;
import com.simple_official_spring_template.dto.ShippingFeeDTO;
import com.simple_official_spring_template.entity.ItemOfOrderEntity;
import com.simple_official_spring_template.entity.OrderEntity;
import com.simple_official_spring_template.entity.ShippingFeeEntity;
import com.simple_official_spring_template.entity.embeddedid.ItemOfOrderId;
import com.simple_official_spring_template.repository.*;
import com.simple_official_spring_template.service.IItemOfOrderService;
import com.simple_official_spring_template.utils.service.IUtilsGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemOfOrderService implements IItemOfOrderService {
    @Autowired
    private IItemOfOrderRepository itemOfOrderRepository;

    @Autowired
    private ItemOfOrderConverter itemOfOrderConverter;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IShippingFeeRepository shippingFeeRepository;

    @Autowired
    private ShippingFeeConverter shippingFeeConverter;

    @Autowired
    private IUserAddressRepository userAddressRepository;

    @Autowired
    private IUtilsGeneralService utilsGeneralService;

    @Override
    public List<ItemOfOrderDTO> findAll() {
        List<ItemOfOrderDTO> itemOfOrderDTOS = new ArrayList<>();

        List<ItemOfOrderEntity> itemOfOrderEntities = itemOfOrderRepository.findAll();
        for (ItemOfOrderEntity itemOfOrderEntity: itemOfOrderEntities) {
            itemOfOrderDTOS.add(itemOfOrderConverter.toDTO(itemOfOrderEntity));
        }

        return itemOfOrderDTOS;
    }

    @Override
    public List<ItemOfOrderDTO> findByOrderId(Integer orderId) {
        List<ItemOfOrderDTO> itemOfOrderDTOS = new ArrayList<>();

        List<ItemOfOrderEntity> itemOfOrderEntities = itemOfOrderRepository.findByOrderId(orderId);
        for (ItemOfOrderEntity itemOfOrderEntity: itemOfOrderEntities) {
            itemOfOrderDTOS.add(itemOfOrderConverter.toDTO(itemOfOrderEntity));
        }

        return itemOfOrderDTOS;
    }

    @Override
    public ItemOfOrderDTO findOneByItemOfOrderId(ItemOfOrderId itemOfOrderId) {
        ItemOfOrderEntity itemOfOrderEntity = itemOfOrderRepository.findOneByItemOfOrderId(itemOfOrderId);
        return itemOfOrderConverter.toDTO(itemOfOrderEntity);
    }

    @Override
    public ItemOfOrderDTO save(ItemOfOrderDTO itemOfOrderDTO) {
        ItemOfOrderEntity itemOfOrderEntity = itemOfOrderConverter.toEntity(itemOfOrderDTO, new ItemOfOrderEntity());
        itemOfOrderEntity = itemOfOrderRepository.save(itemOfOrderEntity);
        

//         update Order
        utilsGeneralService.updateOrder(itemOfOrderEntity.getOrder().getId(), false, false);


        return itemOfOrderConverter.toDTO(itemOfOrderEntity);
    }

    @Override
    public ItemOfOrderDTO update(ItemOfOrderDTO itemOfOrderDTO) {
        ItemOfOrderEntity itemOfOrderEntity = itemOfOrderRepository.findOneByItemOfOrderId(new ItemOfOrderId(itemOfOrderDTO.getProductDTO().getId(), itemOfOrderDTO.getOrderId()));

//        OrderEntity orderEntity = itemOfOrderEntity.getOrder();
//        orderEntity.setTotalPrice(orderEntity.getTotalPrice() - itemOfOrderEntity.getPriceOfItem() * itemOfOrderEntity.getQuantity() + itemOfOrderDTO.getPriceOfItem() * itemOfOrderDTO.getQuantity()  );
//        orderEntity.setTotalWeight(orderEntity.getTotalWeight() - itemOfOrderEntity.getProduct().getProduct().getWeight() * ( itemOfOrderEntity.getQuantity() - itemOfOrderDTO.getQuantity()) );

        itemOfOrderEntity = itemOfOrderConverter.toEntity(itemOfOrderDTO, itemOfOrderEntity);
        itemOfOrderEntity = itemOfOrderRepository.save(itemOfOrderEntity);


        utilsGeneralService.updateOrder(itemOfOrderEntity.getOrder().getId(), false, false);


        return itemOfOrderConverter.toDTO(itemOfOrderEntity);
    }

    @Override
    public void delete(List<ItemOfOrderDTO> itemOfOrderDTOS) {
        OrderEntity orderEntity = orderRepository.findOneById(itemOfOrderDTOS.get(0).getOrderId());
        for (ItemOfOrderDTO itemOfOrderDTO: itemOfOrderDTOS){
            ItemOfOrderEntity itemOfOrderEntity = itemOfOrderRepository.findOneByItemOfOrderId(new ItemOfOrderId(itemOfOrderDTO.getProductDTO().getId(), itemOfOrderDTO.getOrderId()));
//            orderEntity.setTotalPrice(orderEntity.getTotalPrice() - itemOfOrderEntity.getPriceOfItem() * itemOfOrderEntity.getQuantity());
//            orderEntity.setTotalWeight(orderEntity.getTotalWeight() - itemOfOrderEntity.getProduct().getProduct().getWeight() * itemOfOrderEntity.getQuantity() );

            itemOfOrderRepository.deleteByItemOfOrderId(itemOfOrderEntity.getItemOfOrderId());


        }

        // update Order
        utilsGeneralService.updateOrder(orderEntity.getId(), false, false);
    }

    @Override
    public List<ItemOfOrderDTO> findByUserIdAndProductId(String userId, Integer productId) {
        List<ItemOfOrderDTO> itemOfOrderDTOS = new ArrayList<>();

        List<ItemOfOrderEntity> itemOfOrderEntities = itemOfOrderRepository.findByUserIdAndProductId(userId, productId);
        for (ItemOfOrderEntity itemOfOrderEntity: itemOfOrderEntities) {
            itemOfOrderDTOS.add(itemOfOrderConverter.toDTO(itemOfOrderEntity));
        }

        return itemOfOrderDTOS;
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

        if (shippingFeeEntity.getAdditionalPrice() > 0){
            totalPrice += (float)Math.ceil(resultShippingFee.getCurrentWeight() - resultShippingFee.getMinWeight()) * resultShippingFee.getAdditionalPrice();
        }

        return totalPrice;
    }
}
