package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity extends GenericPost{

    @OneToMany(mappedBy = "product")
    private Set<com.simple_official_spring_template.entity.ProductCategoryEntity> productCategoryEntities;

    @OneToMany(mappedBy = "product")
    private Set<com.simple_official_spring_template.entity.ProductProductTypeEntity> productProductTypeEntities;

    @OneToMany(mappedBy = "product")
    private Set<ProductAttributeGroupEntity> productAttributeGroupEntities;


    @Column
    private Double price;

    @Column
    private Double originalPrice;

    @Column
    private Integer quantity;

    @Column
    private Integer warrantyPeriod;

    @Column
    private Double weight;

    @Column
    private Integer rateNumber;

    @Column
    private Double averageRate;

    @OneToMany(mappedBy = "product")
    private Set<ProductRateEntity> productRateEntities;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private com.simple_official_spring_template.entity.PromotionEntity promotion;

    @Column(columnDefinition = "TEXT")
    private String questionAndAnswer;

    @OneToMany(mappedBy = "product")
    private Set<com.simple_official_spring_template.entity.CommentEntity> comments;

    @OneToMany(mappedBy = "product")
    private Set<ItemOfOrderEntity> itemOfOrders;

    @OneToMany(mappedBy = "product")
    private Set<com.simple_official_spring_template.entity.StoreRequestEntity> storeRequests;

    @OneToMany(mappedBy = "product")
    private Set<TransferRequestEntity> transferRequests;

    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private ImageSliderItemEntity imageSliderAvatarItem;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "product_image_slider_item",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_slider_item")
    )
    private List<ImageSliderItemEntity> imageSliderItems;
}
