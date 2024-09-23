package com.excel.shyam.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.excel.shyam.entity.Invoice;
import com.excel.shyam.repository.InvoiceRepository;
import com.excel.shyam.service.IExcelDataService;

public class ExcelDataServiceImpl implements IExcelDataService {

	@Value("${app.upload.file:${user.home}}")
	public String EXCEL_FILE_PATH;

	@Autowired
	InvoiceRepository repo;

	Workbook workbook;

	@Override
	public List<Invoice> getExcelDataAsList() {
		List<String> list = new ArrayList<>();

		// Create a dataformatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// Create the workbook

		try {
			workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH));

		} catch (Exception e) {
			// TODO: handle exception
		}

		// Retrieving the number of sheets in the workbook
		System.out.println("----------Workbook has '" + workbook.getNumberOfSheets() + "' sheets---");

		// Getting the sheet at index Zero
		Sheet sheet = workbook.getSheetAt(0);

		// Getting Number of Column in the Sheet
		short noOfColumns = sheet.getRow(0).getLastCellNum();
		System.out.println("-----------Sheet has '" + noOfColumns + "' Columns");

		// Using for-each loop to iterate over the rows and columns
		for (Row row : sheet) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				list.add(cellValue);
			}
		}

		// filling excel data and creating list as List<Invoice>
		List<Invoice> invList = createList(list, noOfColumns);

		// Closing the workbook
		try {
			workbook.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return invList;
	}

	private List<Invoice> createList(List<String> excelData, short noOfColumns) {
		ArrayList<Invoice> invList = new ArrayList<>();
		int i = noOfColumns;

		do {
			Invoice inv = new Invoice();
			inv.setName(excelData.get(i));
			inv.setAmount(Double.valueOf(excelData.get(i + 1)));
			inv.setNumber(excelData.get(i + 2));
			inv.setReceiveddate(excelData.get(i + 3));

			invList.add(inv);
			i = i + (noOfColumns);
		} while (i < excelData.size());
		return invList;
	}

	@Override
	public int saveExcelData(List<Invoice> invoices) {
		List<Invoice> saveAll = repo.saveAll(invoices);
		return saveAll.size();
	}

}
