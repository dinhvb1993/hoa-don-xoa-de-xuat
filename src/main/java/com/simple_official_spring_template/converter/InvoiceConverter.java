package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.InvoiceDTO;
import com.simple_official_spring_template.entity.InvoiceEntity;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConverter {

    public InvoiceEntity toEntity(InvoiceDTO invoiceDTO, InvoiceEntity invoiceEntity){
        if (invoiceDTO.getId() != null){
            invoiceEntity.setId(invoiceDTO.getId());
        }
        if (invoiceDTO.getFullDate() != null){
            invoiceEntity.setFullDate(invoiceDTO.getFullDate());
        }
        if (invoiceDTO.getMoney() != null){
            invoiceEntity.setMoney(invoiceDTO.getMoney());
        }
        if (invoiceDTO.getOriginalLink() != null){
            invoiceEntity.setOriginalLink(invoiceDTO.getOriginalLink());
        }
        if (invoiceDTO.getCustomerId() != null){
            invoiceEntity.setCustomerId(invoiceDTO.getCustomerId());
        }
        if (invoiceDTO.getCustomerName() != null){
            invoiceEntity.setCustomerName(invoiceDTO.getCustomerName());
        }
        if (invoiceDTO.getFileUrl() != null){
            invoiceEntity.setFileUrl(invoiceDTO.getFileUrl());
        }
        if (invoiceDTO.getYear() != null){
            invoiceEntity.setYear(invoiceDTO.getYear());
        }
        if (invoiceDTO.getMonth() != null){
            invoiceEntity.setMonth(invoiceDTO.getMonth());
        }
        if (invoiceDTO.getDate() != null){
            invoiceEntity.setDate(invoiceDTO.getDate());
        }
        return invoiceEntity;
    }

    public InvoiceDTO toDTO(InvoiceEntity invoiceEntity){
        return new InvoiceDTO(
                invoiceEntity.getId(),
                invoiceEntity.getFullDate(),
                invoiceEntity.getMoney(),
                invoiceEntity.getOriginalLink(),
                invoiceEntity.getCustomerId(),
                invoiceEntity.getCustomerName(),
                invoiceEntity.getFileUrl(),
                invoiceEntity.getYear(),
                invoiceEntity.getMonth(),
                invoiceEntity.getDate(),
                null
        );
    }
}
