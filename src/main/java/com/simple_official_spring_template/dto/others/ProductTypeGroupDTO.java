package com.simple_official_spring_template.dto.others;

import com.simple_official_spring_template.dto.ProductTypeDTO;
import com.simple_official_spring_template.repository.IProductTypeRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductTypeGroupDTO implements Comparable<ProductTypeGroupDTO>{
//    private Integer productTypeId;
//    private Integer orderNumber;
    private ProductTypeDTO productTypeDTO;
    private List<Integer> childProductTypeIds;
    private List<Integer> allChildProductTypeIds;
    private List<ProductTypeGroupDTO> childProductTypeGroupDTO;
    private String compareFeild;

    @Autowired
    private IProductTypeRepository productTypeRepository;

    public ProductTypeGroupDTO() {
    }

    public ProductTypeGroupDTO(ProductTypeDTO productTypeDTO) {
        this.productTypeDTO = productTypeDTO;
        this.childProductTypeIds = new ArrayList<>();
        this.allChildProductTypeIds = new ArrayList<>();
        this.childProductTypeGroupDTO = new ArrayList<>();
    }

    @Override
    public int compareTo(ProductTypeGroupDTO o) {
//        return this.productTypeDTO.compareTo(o.productTypeDTO);
        return this.productTypeDTO.compareTo(o.getProductTypeDTO());
    }
}
