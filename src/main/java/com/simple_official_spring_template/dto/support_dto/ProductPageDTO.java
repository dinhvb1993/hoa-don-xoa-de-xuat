package com.simple_official_spring_template.dto.support_dto;

import com.simple_official_spring_template.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPageDTO {
    private List<ProductDTO> productDTOList;
    private long totalElements;
    private int totalPages;
}
