package com.simple_official_spring_template.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.Collator;
import java.util.Locale;

@Getter
@Setter
public abstract class GenericCategoryDTO implements Comparable<GenericCategoryDTO>{
    private Integer id;
    private String name;
    private String slug;
    private Integer orderNumber;
    private String description;
    private String compareFeild;
    private Boolean showHome;
    private Timestamp creationTime;
    private Timestamp updateTime;
    private int degree;
    private Integer[] ids;


    // addition
    private String exceptionNote;


    public GenericCategoryDTO() {
    }

    public GenericCategoryDTO(Integer id, String name, String slug, Integer orderNumber, String description, String compareFeild, Boolean showHome, Timestamp creationTime, Timestamp updateTime, int degree) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.orderNumber = orderNumber;
        this.description = description;
        this.compareFeild = compareFeild;
        this.showHome = showHome;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.degree = degree;
    }


    @Override
    public int compareTo(GenericCategoryDTO o) {
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
