package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class CategoryDTO extends GenericCategoryDTO {

    private CategoryDTO parentCategoryDTO;
    private ImageSliderItemDTO imageSliderAvatarItemDTO;
    private List<ImageSliderItemDTO> imageSliderItemDTOS;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer id, String name, String slug, Integer orderNumber, String description, String compareFeild, Boolean showHome, Timestamp creationTime, Timestamp updateTime, int degree, CategoryDTO parentCategoryDTO, ImageSliderItemDTO imageSliderAvatarItemDTO, List<ImageSliderItemDTO> imageSliderItemDTOS) {
        super(id, name, slug, orderNumber, description, compareFeild, showHome, creationTime, updateTime, degree);
        this.parentCategoryDTO = parentCategoryDTO;
        this.imageSliderAvatarItemDTO = imageSliderAvatarItemDTO;
        this.imageSliderItemDTOS = imageSliderItemDTOS;
    }

    //    override if wanna custom
//    @Override
//    public int compareTo(CategoryDTO o) {
//        Object value1 = null;
//        Object value2 = null;
//
//        if (super.getCompareFeild() == null){
//            super.setCompareFeild("order_number");
//        }
//
//        if (super.getCompareFeild().equals("order_number")){
//            value1 = this.getOrderNumber();
//            value2 = o.getOrderNumber();
//        }
//        else if (super.getCompareFeild().equals("name")){
//            value1 = this.getName();
//            value2 = o.getName();
//        }
//
//        if (value1 == null && value2 == null){
//            return 0;
//        }
//        else if (value1 == null && value2 != null){
//            return -1;
//        }
//        else if (value1 != null && value2 == null){
//            return 1;
//        }
//
//        if (value1 instanceof Integer){
//            return (int)value1 - (int)value2;
//        }
//        else if (value1 instanceof String){
//            Collator usCollator = Collator.getInstance(new Locale("vi", "VN"));
//            usCollator.setStrength(Collator.PRIMARY);
//            return usCollator.compare((String)value1, (String)value2);
//        }
//        return 0;
//    }
}
