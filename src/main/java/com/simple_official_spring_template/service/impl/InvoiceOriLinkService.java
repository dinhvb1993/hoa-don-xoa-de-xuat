package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.InvoiceOriLinkConverter;
import com.simple_official_spring_template.dto.InvoiceOriLinkDTO;
import com.simple_official_spring_template.entity.InvoiceOriLinkEntity;
import com.simple_official_spring_template.repository.IInvoiceOriLinkRepository;
import com.simple_official_spring_template.service.IInvoiceOriLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class InvoiceOriLinkService implements IInvoiceOriLinkService {

    @Autowired
    private IInvoiceOriLinkRepository invoiceOriLinkRepository;

    @Autowired
    private InvoiceOriLinkConverter invoiceOriLinkConverter;


    @Override
    public InvoiceOriLinkDTO save(InvoiceOriLinkDTO invoiceOriLinkDTO) {
        InvoiceOriLinkEntity invoiceOriLinkEntity = invoiceOriLinkConverter.toEntity(invoiceOriLinkDTO, new InvoiceOriLinkEntity());
        return invoiceOriLinkConverter.toDTO(invoiceOriLinkRepository.save(invoiceOriLinkEntity));
    }

    @Override
    public InvoiceOriLinkDTO update(InvoiceOriLinkDTO invoiceOriLinkDTO) {
        InvoiceOriLinkEntity invoiceOriLinkEntity = invoiceOriLinkRepository.findOneById(invoiceOriLinkDTO.getId());
        invoiceOriLinkEntity = invoiceOriLinkConverter.toEntity(invoiceOriLinkDTO, invoiceOriLinkEntity);
        return invoiceOriLinkConverter.toDTO(invoiceOriLinkRepository.save(invoiceOriLinkEntity));
    }

    @Override
    public InvoiceOriLinkDTO findOneById(Integer id) {
        return invoiceOriLinkConverter.toDTO(invoiceOriLinkRepository.findOneById(id));
    }

    @Override
    public List<InvoiceOriLinkDTO> findAll() {
        List<InvoiceOriLinkDTO> invoiceOriLinkDTOS = new ArrayList<>();
        for (InvoiceOriLinkEntity invoiceOriLinkEntity: invoiceOriLinkRepository.findAll()){
            invoiceOriLinkDTOS.add(invoiceOriLinkConverter.toDTO(invoiceOriLinkEntity));
        }
        return invoiceOriLinkDTOS;
    }

    @Override
    public void delete(InvoiceOriLinkDTO invoiceOriLinkDTO) {
        invoiceOriLinkRepository.deleteByIds(invoiceOriLinkDTO.getIds());
    }

    @Override
    public int saveMultipleLinks(String links) {
        int counter = 0;
        links = links.replaceAll(" ", "");
        for (String link: links.split(",")){
            if (invoiceOriLinkRepository.findOneByLink(link) == null){
                InvoiceOriLinkEntity invoiceOriLinkEntity = new InvoiceOriLinkEntity();
                invoiceOriLinkEntity.setLink(link);
                invoiceOriLinkRepository.save(invoiceOriLinkEntity);
                counter++;
            }
        }
        return counter;
    }


}
