package com.simple_official_spring_template.dto;

import com.simple_official_spring_template.dto.support_dto.NumberBooleanDTO;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.Collator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Getter
@Setter
public abstract class GenericPostDTO implements Comparable<GenericPostDTO>{
    private Integer id;
    private String name;
    private String slug;
    private String description;
    private String content;
    private Integer orderNumber;
    private Timestamp creationTime;
    private Timestamp updateTime;
    private Boolean isHidden;
    private String compareFeild;
    private Integer[] ids;


    public GenericPostDTO() {
    }

    public GenericPostDTO(Integer id, String name, String slug, String description, String content, Integer orderNumber, Timestamp creationTime, Timestamp updateTime, Boolean isHidden) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.content = content;
        this.orderNumber = orderNumber;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.isHidden = isHidden;
    }

    @Override
    public int compareTo(GenericPostDTO o) {
        Object value1 = null;
        Object value2 = null;

        if (this.compareFeild == null){
            this.compareFeild = "order_number";
        }

        if (this.compareFeild.equals("order_number")){
            value1 = this.getOrderNumber();
            value2 = o.getOrderNumber();
        }
        else if (this.compareFeild.equals("name")){
            value1 = this.getName();
            value2 = o.getName();
        }

        if (value1 == null && value2 == null){
            return 0;
        }
        else if (value1 == null && value2 != null){
            return -1;
        }
        else if (value1 != null && value2 == null){
            return 1;
        }

        if (value1 instanceof Integer){
            return (int)value1 - (int)value2;
        }
        else if (value1 instanceof String){
            Collator usCollator = Collator.getInstance(new Locale("vi", "VN"));
            usCollator.setStrength(Collator.PRIMARY);
            return usCollator.compare((String)value1, (String)value2);
        }
        return 0;
    }
}
