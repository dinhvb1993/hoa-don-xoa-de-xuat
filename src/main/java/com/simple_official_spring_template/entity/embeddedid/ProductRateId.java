package com.simple_official_spring_template.entity.embeddedid;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ProductRateId implements Serializable {
    private String userId;
    private Integer productId;

    public ProductRateId() {
        super();
    }

    public ProductRateId(String userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
    }
}
