package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "attribute_group")
public class AttributeGroupEntity extends GenericCategory{

    // generate new similar file ~ category class (entity, dto, converter, repository, iservice, service, api),
    // & write to product class (entity, dto, converter, repository, iservice, service, api)
    // & write to GeneralNativeQuery, CategoryGroup

    @OneToOne
    @JoinColumn(name = "parent_id")
    private AttributeGroupEntity parent;

    @OneToMany(mappedBy = "attributeGroup")
    private List<ProductAttributeGroupEntity> productAttributeGroupEntities;


    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private ImageSliderItemEntity imageSliderAvatarItem;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
//            name = "attribute_image_slider_item",
            joinColumns = @JoinColumn(name = "attribute_group_id"),
            inverseJoinColumns = @JoinColumn(name = "image_slider_item")
    )
    private List<ImageSliderItemEntity> imageSliderItems;

}
