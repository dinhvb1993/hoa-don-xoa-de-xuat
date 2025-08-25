package com.simple_official_spring_template.entity;

import com.simple_official_spring_template.entity.embeddedid.ItemOfOrderId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "item_of_order")
public class ItemOfOrderEntity {
    @EmbeddedId
    private ItemOfOrderId itemOfOrderId;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private com.simple_official_spring_template.entity.ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private com.simple_official_spring_template.entity.OrderEntity order;

    @Column(nullable = false)
    private Integer quantity;

    @Column
    private Double priceOfItem;

    @Column
    private String promotionCode;

    @Column
    private Integer applyingNumber;

    @Column
    private Double deduction;

    @Column
    private Double finalPrice;

    @Column
    private Integer errorCode;
}
