package com.simple_official_spring_template.api;

import com.simple_official_spring_template.dto.InvoiceOriLinkDTO;
import com.simple_official_spring_template.dto.others.AdditionDTO;
import com.simple_official_spring_template.service.IInvoiceOriLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceOriLinkAPI {
    @Autowired
    private IInvoiceOriLinkService invoiceOriLinkService;

    @GetMapping(value = "/api-invoice-ori-link/{id}")
    public InvoiceOriLinkDTO getInvoiceOriLinkById(@PathVariable(value = "id", required = true) Integer id){
        return invoiceOriLinkService.findOneById(id);
    }

    @GetMapping(value = "/api-invoice-ori-link")
    public List<InvoiceOriLinkDTO> getInvoiceOriLinks(){
        return invoiceOriLinkService.findAll();
    }


    @PostMapping(value = "/api-invoice-ori-link")
    public InvoiceOriLinkDTO createInvoiceOriLink(@RequestBody InvoiceOriLinkDTO invoiceOriLinkDTO){
        return invoiceOriLinkService.save(invoiceOriLinkDTO);
    }

    @PostMapping(value = "/api-invoice-ori-link/save-multiple-links")
    public int saveMultipleLinks(@RequestBody AdditionDTO additionDTO){
        return invoiceOriLinkService.saveMultipleLinks(additionDTO.getStringTemplate());
    }

    @PutMapping(value = "/api-invoice-ori-link")
    public InvoiceOriLinkDTO updateInvoiceOriLink(@RequestBody InvoiceOriLinkDTO invoiceOriLinkDTO){
        return invoiceOriLinkService.update(invoiceOriLinkDTO);
    }

    @DeleteMapping(value = "/api-invoice-ori-link")
    public void deleteInvoiceOriLinks(@RequestBody InvoiceOriLinkDTO invoiceOriLinkDTO){
        invoiceOriLinkService.delete(invoiceOriLinkDTO);
    }
}
