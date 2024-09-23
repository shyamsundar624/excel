package com.excel.shyam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excel.shyam.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
