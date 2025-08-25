package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "image_slider_item")
public class ImageSliderItemEntity extends com.simple_official_spring_template.entity.GenericImageSliderItem {
    @ManyToMany(mappedBy = "imageSliderItems")
    private List<CategoryEntity> categories;

    @ManyToMany(mappedBy = "imageSliderItems")
    private List<com.simple_official_spring_template.entity.ProductEntity> products;

    // clone with new category
    @ManyToMany(mappedBy = "imageSliderItems")
    private List<com.simple_official_spring_template.entity.ProductTypeEntity> productTypes;
    // --
    @ManyToMany(mappedBy = "imageSliderItems")
    private List<com.simple_official_spring_template.entity.ProductTypeEntity> attributeGroups;
    // end clone



    @OneToMany(mappedBy = "imageSliderAvatarItem")
    private List<CategoryEntity> avatarCategories;

    @OneToMany(mappedBy = "imageSliderAvatarItem")
    private List<com.simple_official_spring_template.entity.ProductEntity> avatarProducts;

    // clone with category
    @OneToMany(mappedBy = "imageSliderAvatarItem")
    private List<com.simple_official_spring_template.entity.ProductTypeEntity> avatarProductTypes;
    // ----
    @OneToMany(mappedBy = "imageSliderAvatarItem")
    private List<com.simple_official_spring_template.entity.AttributeGroupEntity> avatarAttributeGroups;
    // end clone
}
