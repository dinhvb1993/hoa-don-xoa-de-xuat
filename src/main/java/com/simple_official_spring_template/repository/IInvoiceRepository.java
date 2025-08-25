package com.simple_official_spring_template.repository;

import com.simple_official_spring_template.entity.InvoiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IInvoiceRepository extends JpaRepository<InvoiceEntity, String> {
    InvoiceEntity findOneById(String id);


    Page<InvoiceEntity> findByYear(String year, Pageable pageable);
    Page<InvoiceEntity> findByYearAndMonth(String year, String month, Pageable pageable);
    Page<InvoiceEntity> findByYearAndMonthAndDate(String year, String month, String date, Pageable pageable);


    Page<InvoiceEntity> findByYearAndCustomerId(String year, String customerId, Pageable pageable);
    Page<InvoiceEntity> findByYearAndMonthAndCustomerId(String year, String month, String customerId, Pageable pageable);
    Page<InvoiceEntity> findByYearAndMonthAndDateAndCustomerId(String year, String month, String date, String customerId, Pageable pageable);


    Page<InvoiceEntity> findByCustomerId(String customerId, Pageable pageable);


    @Query(nativeQuery =true, value = "DELETE FROM invoice as t WHERE t.id IN :ids")
    void deleteByIds(@Param("ids") String[] ids);
}
