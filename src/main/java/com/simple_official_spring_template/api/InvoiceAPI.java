package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.InvoiceDTO;
import com.simple_official_spring_template.dto.others.GroupInvoiceDTO;
import com.simple_official_spring_template.dto.others.PageInvoiceDTO;
import com.simple_official_spring_template.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceAPI {
    @Autowired
    private IInvoiceService invoiceService;

    @GetMapping(value = "/api-invoice/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable(value = "id", required = true) String id){
        return invoiceService.findOneById(id);
    }


    @GetMapping(value = "/api-invoice")
    public List<InvoiceDTO> getInvoices(){
        return invoiceService.findAll();
    }


    @PostMapping(value = "/api-invoice")
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO invoiceDTO){
        return invoiceService.save(invoiceDTO);
    }

    @PutMapping(value = "/api-invoice")
    public InvoiceDTO updateInvoice(@RequestBody InvoiceDTO invoiceDTO){
        return invoiceService.update(invoiceDTO);
    }

    @DeleteMapping(value = "/api-invoice")
    public void deleteInvoices(@RequestBody InvoiceDTO invoiceDTO){
        invoiceService.delete(invoiceDTO);
    }


    @GetMapping(value = "/api-invoice/crawl-data")
    public void crawlInvoice(){
        invoiceService.crawlInvoice();
    }




    @GetMapping(value = "/api-invoice/page")
    public PageInvoiceDTO getPageInvoice(
            @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "order_by", required = false) String orderBy, @RequestParam(value = "order_type", required = false) String orderType,
            @RequestParam(value = "year", required = false) String year, @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "date", required = false) String date
    ){

        if (page == null || limit == null){
            page = 1;
            limit = Integer.MAX_VALUE;
        }

        Pageable pageable;
        Sort sort;
        if (orderBy == null || orderType == null){
            pageable = PageRequest.of(page-1, limit);
        }
        else {
            if (orderType.equals("desc")){
                sort = Sort.by(orderBy).descending();
            }
            else {
                sort = Sort.by(orderBy).ascending();
            }

            pageable = PageRequest.of(page-1, limit, sort);
        }

        if (year != null){

            if (month == null){
                return invoiceService.findByYear(year, pageable);
            }
            else {
                if (date == null){
                    return invoiceService.findByYearAndMonth(year, month, pageable);
                }
                return invoiceService.findByYearAndMonthAndDate(year, month, date, pageable);
            }

        }

        return invoiceService.findPageInvoice(pageable);
    }


    @GetMapping(value = "/api-invoice/find-custom")
    public PageInvoiceDTO getPageCustomInvoice(
            @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "order_by", required = false) String orderBy, @RequestParam(value = "order_type", required = false) String orderType,
            @RequestParam(value = "year", required = false) String year, @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "date", required = false) String date, @RequestParam(value = "customerId", required = false) String customerId
    ){

        if (page == null || limit == null){
            page = 1;
            limit = Integer.MAX_VALUE;
        }

        Pageable pageable;
        Sort sort;
        if (orderBy == null || orderType == null){
            pageable = PageRequest.of(page-1, limit);
        }
        else {
            if (orderType.equals("desc")){
                sort = Sort.by(orderBy).descending();
            }
            else {
                sort = Sort.by(orderBy).ascending();
            }

            pageable = PageRequest.of(page-1, limit, sort);
        }


        return invoiceService.findCustom(year, month, date, customerId, pageable);
    }

    @GetMapping(value = "/api-invoice/group-invoice")
    public List<GroupInvoiceDTO> groupInvoice(
            @RequestParam(value = "year", required = false) String year, @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "date", required = false) String date
    ){
        return invoiceService.groupInvoice(year, month, date);
    }


    @GetMapping(value = "/api-invoice/delete-recommend-ads")
    public void deleteRecommendAds(){
        invoiceService.deleteRecommendAds();
    }

    @GetMapping(value = "/api-invoice/tracking-link")
    public void trackingLink(){
        invoiceService.trackingLink();
    }





}
