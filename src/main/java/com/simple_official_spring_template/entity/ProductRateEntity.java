package com.simple_official_spring_template.entity;

import com.simple_official_spring_template.entity.embeddedid.ProductRateId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_rate")
public class ProductRateEntity {
    @EmbeddedId
    private ProductRateId productRateId;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private com.simple_official_spring_template.entity.ProductEntity product;

    private Double rate;
}
