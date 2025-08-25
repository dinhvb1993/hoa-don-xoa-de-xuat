package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.InvoiceOriLinkDTO;

import java.util.List;

public interface IInvoiceOriLinkService {
    InvoiceOriLinkDTO save(InvoiceOriLinkDTO invoiceOriLinkDTO);
    InvoiceOriLinkDTO update(InvoiceOriLinkDTO invoiceOriLinkDTO);
    InvoiceOriLinkDTO findOneById(Integer id);
    List<InvoiceOriLinkDTO> findAll();
    void delete(InvoiceOriLinkDTO invoiceOriLinkDTO);

    int saveMultipleLinks(String links);
}
