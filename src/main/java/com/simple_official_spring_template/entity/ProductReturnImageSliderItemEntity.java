package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "product_return_image_slider_item")
public class ProductReturnImageSliderItemEntity extends com.simple_official_spring_template.entity.GenericImageSliderItem {
    @ManyToOne
    @JoinColumn(name = "returned_variant_product_id")
    private com.simple_official_spring_template.entity.ReturnedProductEntity returnedVariantProduct;
}
