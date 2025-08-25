package com.simple_official_spring_template.entity.embeddedid;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ItemOfOrderId implements Serializable {
    private Integer productId;
    private Integer orderId;

    public ItemOfOrderId() {

    }

    public ItemOfOrderId(Integer productId, Integer orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }
}
