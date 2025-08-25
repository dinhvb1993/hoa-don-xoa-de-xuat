package com.simple_official_spring_template.service;

import com.simple_official_spring_template.dto.InvoiceDTO;
import com.simple_official_spring_template.dto.others.GroupInvoiceDTO;
import com.simple_official_spring_template.dto.others.PageInvoiceDTO;
import com.simple_official_spring_template.entity.InvoiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public interface IInvoiceService {
    InvoiceDTO save(InvoiceDTO invoiceDTO);
    InvoiceDTO update(InvoiceDTO invoiceDTO);
    InvoiceDTO findOneById(String id);
    List<InvoiceDTO> findAll();
    void delete(InvoiceDTO invoiceDTO);


    void crawlInvoice();

    PageInvoiceDTO findPageInvoice(Pageable pageable);
    PageInvoiceDTO findByYear(String year, Pageable pageable);
    PageInvoiceDTO findByYearAndMonth(String year, String month, Pageable pageable);
    PageInvoiceDTO findByYearAndMonthAndDate(String year, String month, String date, Pageable pageable);



    PageInvoiceDTO findCustom(String year, String month, String date, String customerId, Pageable pageable);

    List<GroupInvoiceDTO> groupInvoice(String year, String month, String date);


    void deleteRecommendAds();

    void trackingLink();

}
