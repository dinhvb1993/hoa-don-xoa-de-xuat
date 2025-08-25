package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.OrderConverter;
import com.simple_official_spring_template.converter.OrderStatusConverter;
import com.simple_official_spring_template.dto.OrderStatusDTO;
import com.simple_official_spring_template.entity.*;
import com.simple_official_spring_template.repository.*;
import com.simple_official_spring_template.service.IOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatusService implements IOrderStatusService {
    @Autowired
    private IOrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderStatusConverter orderStatusConverter;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private ICommonRepository commonRepository;

    @Autowired
    private IItemOfOrderRepository itemOfOrderRepository;

    @Autowired
    private OrderConverter orderConverter;

    @Override
    public List<OrderStatusDTO> findAll() {
        List<OrderStatusDTO> orderStatusDTOS = new ArrayList<>();

        List<OrderStatusEntity> orderStatusEntities = orderStatusRepository.findAll();
        for (OrderStatusEntity orderStatusEntity: orderStatusEntities) {
            orderStatusDTOS.add(orderStatusConverter.toDTO(orderStatusEntity));
        }

        return orderStatusDTOS;
    }

    @Override
    public List<OrderStatusDTO> findByOrderId(Integer orderId) {
        List<OrderStatusDTO> orderStatusDTOS = new ArrayList<>();

        List<OrderStatusEntity> orderStatusEntities = orderStatusRepository.findByOrderIdOrderByTimeAsc(orderId);
        for (OrderStatusEntity orderStatusEntity: orderStatusEntities) {
            orderStatusDTOS.add(orderStatusConverter.toDTO(orderStatusEntity));
        }

        return orderStatusDTOS;
    }

    @Override
    public OrderStatusDTO findOneById(Integer id) {
        OrderStatusEntity orderStatusEntity = orderStatusRepository.findOneById(id);
        return orderStatusConverter.toDTO(orderStatusEntity);
    }

    @Override
    public OrderStatusDTO save(OrderStatusDTO orderStatusDTO) {
        OrderStatusEntity orderStatusEntity = orderStatusConverter.toEntity(orderStatusDTO, new OrderStatusEntity());
        orderStatusEntity = orderStatusRepository.save(orderStatusEntity);

        OrderEntity orderEntity = orderStatusEntity.getOrder();
        orderEntity.setCurrentStatusId(orderStatusEntity.getStatus().getId());
        orderRepository.save(orderEntity);

//        OrderEntity orderEntity = orderStatusEntity.getOrder();
//        orderEntity.setCurrentOrderStatus(Status.valueOf(orderStatusEntity.getStatus().getName()));
//
//        if (Status.valueOf(orderStatusEntity.getStatus().getName()) == ORDER_APPROVED){
//            CommonEntity commonEntity = commonRepository.findAll().get(0);
//            Integer minIndexWarehouseManager = commonEntity.getMinIndexWarehouseManager();
//            WarehouseManagerEntity chosenWarehouseManager = warehouseManagerRepository.findTopByCurrentIndex(minIndexWarehouseManager);
//            if (chosenWarehouseManager == null){
//                commonEntity.setMinIndexWarehouseManager(commonEntity.getMinIndexWarehouseManager() + 1);
//                commonRepository.save(commonEntity);
//                chosenWarehouseManager = warehouseManagerRepository.findTopByCurrentIndex(minIndexWarehouseManager + 1);
//            }
//            chosenWarehouseManager.setCurrentIndex(chosenWarehouseManager.getCurrentIndex() + 1);
//            warehouseManagerRepository.save(chosenWarehouseManager);
//            orderEntity.setWarehouseManager(chosenWarehouseManager);
//
//            List<ItemOfOrderEntity> itemOfOrderEntities = itemOfOrderRepository.findByOrderId(orderEntity.getId());
//            for (ItemOfOrderEntity itemOfOrderEntity: itemOfOrderEntities){
//                if (orderOfShopRepository.findOneByShopIdAndOrderId(itemOfOrderEntity.getProductOfShop().getShop().getId(), orderEntity.getId()) == null){
//                    orderOfShopRepository.save(orderOfShopConverter.toEntity(new OrderOfShopDTO(orderConverter.toDTO(orderEntity), itemOfOrderEntity.getProductOfShop().getShop().getId()), new OrderOfShopEntity()));
//                }
//            }
//        }
//
//        orderEntity = orderRepository.save(orderEntity);

        return orderStatusConverter.toDTO(orderStatusEntity);
    }

    @Override
    public OrderStatusDTO update(OrderStatusDTO orderStatusDTO) {
        OrderStatusEntity orderStatusEntity = orderStatusRepository.findOneById(orderStatusDTO.getId());
        orderStatusEntity = orderStatusConverter.toEntity(orderStatusDTO, orderStatusEntity);
        orderStatusEntity = orderStatusRepository.save(orderStatusEntity);
        return orderStatusConverter.toDTO(orderStatusEntity);
    }
}
