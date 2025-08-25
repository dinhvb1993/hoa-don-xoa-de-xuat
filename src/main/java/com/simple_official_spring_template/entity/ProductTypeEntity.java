package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product_type")
public class ProductTypeEntity extends GenericCategory{

    // generate new similar file ~ category class (entity, dto, converter, repository, iservice, service, api),
    // & write to product class (entity, dto, converter, repository, iservice, service, api)
    // & write to GeneralNativeQuery, CategoryGroupDTO, ImgaeSliderItemEntity

    @OneToOne
    @JoinColumn(name = "parent_id")
    private ProductTypeEntity parent;

//    @ManyToMany(mappedBy = "productTypes")
//    private List<ProductEntity> products;

    @OneToMany(mappedBy = "productType")
    private Set<com.simple_official_spring_template.entity.ProductProductTypeEntity> productProductTypeEntities;

    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private ImageSliderItemEntity imageSliderAvatarItem;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
//            name = "product_type_image_slider_item",
            joinColumns = @JoinColumn(name = "product_type_id"),
            inverseJoinColumns = @JoinColumn(name = "image_slider_item")
    )
    private List<ImageSliderItemEntity> imageSliderItems;

}
