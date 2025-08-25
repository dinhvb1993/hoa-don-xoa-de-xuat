package com.simple_official_spring_template.dto.others;

import com.simple_official_spring_template.dto.InvoiceDTO;
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

public class PageInvoiceDTO {
    private int page;
    private int totalPages;
    private long totalItems;
    private Double totalMoney;
    private List<InvoiceDTO> listResult;
}
