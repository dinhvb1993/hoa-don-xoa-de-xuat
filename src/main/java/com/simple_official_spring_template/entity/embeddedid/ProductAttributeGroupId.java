package com.simple_official_spring_template.entity.embeddedid;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ProductAttributeGroupId implements Serializable {
    private Integer productId;
    private Integer attributeGroupId;

    public ProductAttributeGroupId() {
    }

    public ProductAttributeGroupId(Integer productId, Integer attributeGroupId) {
        this.productId = productId;
        this.attributeGroupId = attributeGroupId;
    }
}
