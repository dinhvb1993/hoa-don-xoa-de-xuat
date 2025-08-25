package com.simple_official_spring_template.entity.embeddedid;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ProductProductTypeId implements Serializable {
    private Integer productId;
    private Integer productTypeId;

    public ProductProductTypeId() {
    }

    public ProductProductTypeId(Integer productId, Integer productTypeId) {
        this.productId = productId;
        this.productTypeId = productTypeId;
    }
}
