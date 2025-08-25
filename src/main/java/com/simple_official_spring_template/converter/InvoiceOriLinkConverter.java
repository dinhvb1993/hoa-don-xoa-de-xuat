package com.simple_official_spring_template.converter;

import com.simple_official_spring_template.dto.InvoiceOriLinkDTO;
import com.simple_official_spring_template.entity.InvoiceOriLinkEntity;
import org.springframework.stereotype.Component;

@Component
public class InvoiceOriLinkConverter {

    public InvoiceOriLinkEntity toEntity(InvoiceOriLinkDTO invoiceOriLinkDTO, InvoiceOriLinkEntity invoiceOriLinkEntity){
        if (invoiceOriLinkDTO.getId() != null){
            invoiceOriLinkEntity.setId(invoiceOriLinkDTO.getId());
        }
        if (invoiceOriLinkDTO.getLink() != null){
            invoiceOriLinkEntity.setLink(invoiceOriLinkDTO.getLink());
        }

        return invoiceOriLinkEntity;
    }

    public InvoiceOriLinkDTO toDTO(InvoiceOriLinkEntity invoiceOriLinkEntity){
        return new InvoiceOriLinkDTO(
                invoiceOriLinkEntity.getId(),
                invoiceOriLinkEntity.getLink(),
                null
        );
    }
}
