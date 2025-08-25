package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    private Double totalWeight;

    @Column
    private Double shippingFee;

    @Column
    private Double finalPrice;

    @Column
    private Boolean isAvailable;

//    @Enumerated(value = EnumType.STRING)
    private String currentStatusId;

    @OneToMany(mappedBy = "order")
    private Set<com.simple_official_spring_template.entity.OrderStatusEntity> orderStatuses;

    @OneToMany(mappedBy = "order")
    private Set<ItemOfOrderEntity> itemOfOrders;

//    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
//    private PaymentEntity payment;

//    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
//    private TransportationEntity transportation;

    @OneToMany(mappedBy = "order")
    private Set<ProductReturnEntity> productReturns;


    @Column
    private Timestamp paymentTime;
}
