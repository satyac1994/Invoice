package com.ecart.invoice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecart.invoice.entity.InvoiceEntity;

@Repository
public interface InvoiceRepository extends CrudRepository<InvoiceEntity, Long> {

}
