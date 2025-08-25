package com.simple_official_spring_template.dto.others;

import com.simple_official_spring_template.dto.InvoiceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GroupInvoiceDTO {
    private String customerId;
    private String customerName;
    private String originalLink;
    private Double totalMoney;
    private long count;
}
