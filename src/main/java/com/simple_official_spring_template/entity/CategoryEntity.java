package com.simple_official_spring_template.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity extends com.simple_official_spring_template.entity.GenericCategory {

    @OneToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntity parent;

//    @ManyToMany(mappedBy = "categories")
//    private List<ProductEntity> products;

    @OneToMany(mappedBy = "category")
    private Set<com.simple_official_spring_template.entity.ProductCategoryEntity> productCategoryEntities;

    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private com.simple_official_spring_template.entity.ImageSliderItemEntity imageSliderAvatarItem;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Quan hệ n-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    @JoinTable(
//            name = "category_image_slider_item", //Tạo ra một join Table tên là "address_person"
            joinColumns = @JoinColumn(name = "category_id"),  // TRong đó, khóa ngoại chính là image_slider_item_id trỏ tới class hiện tại (ImageSliderItemEntity)
            inverseJoinColumns = @JoinColumn(name = "image_slider_item") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (category)
    )
    private List<com.simple_official_spring_template.entity.ImageSliderItemEntity> imageSliderItems;

}
