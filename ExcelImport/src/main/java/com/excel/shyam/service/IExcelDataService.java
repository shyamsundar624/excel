package com.excel.shyam.service;

import java.util.List;

import com.excel.shyam.entity.Invoice;

public interface IExcelDataService {

	List<Invoice> getExcelDataAsList();
	
	int saveExcelData(List<Invoice> invoices);
}
