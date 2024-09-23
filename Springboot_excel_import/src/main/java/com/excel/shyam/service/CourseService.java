package com.excel.shyam.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excel.shyam.entity.Course;
import com.excel.shyam.repo.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository repository;

	public void generateExcel(HttpServletResponse response) throws IOException {
		List<Course> courses = repository.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("COurse Info");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("PRICE");

		int dataRowIndex = 1;

		for (Course course : courses) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(course.getCid());
			dataRow.createCell(1).setCellValue(course.getCName());
			dataRow.createCell(2).setCellValue(course.getCPrice());
			dataRowIndex++;

		}
		ServletOutputStream outputStream = response.getOutputStream();

		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	public void save(Course course) {
		repository.save(course);
	}

	public void generateSheet(HttpServletResponse response) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();

		String[] headerContent = { "ID", "Name", "Course", "Price", "City", "DOJ" };
		XSSFSheet sheet = workbook.createSheet("CourseInfo");
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell;
		XSSFCellStyle style1 = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 12);
		int cellIndex = 0;
		for (String cellHeader : headerContent) {
			cell = row.createCell(cellIndex);
			cell.setCellValue(cellHeader);
			cell.setCellType(CellType.STRING);
			style1.setFillBackgroundColor(IndexedColors.BLUE.index);
			style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style1.setAlignment(HorizontalAlignment.CENTER);
			cell.setCellStyle(style1);
			sheet.setColumnWidth(cellIndex, (15 * 550));
			cellIndex++;
		}
		CellRangeAddressList cellRange=new CellRangeAddressList(0,0,4,4);
		DataValidationHelper dvHelper=sheet.getDataValidationHelper();
		DataValidationConstraint dvConstraint=dvHelper.createExplicitListConstraint(new String[] {"Rosera","Samastipur","Patna"});
		
		DataValidation validation=dvHelper.createValidation(dvConstraint, cellRange);
		//set the dropdown cell to allow list Validation
		sheet.addValidationData(validation);
		ServletOutputStream outputStream = response.getOutputStream();

		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	public void addDropdown(HttpServletResponse response) throws Exception {
//Specify the path of your excel file
String filePath="D:\\Doubt\\course.xls";

//Load the Workbook
	FileInputStream fileInputStream=new FileInputStream(new File(filePath));
	
	Workbook workbook=new XSSFWorkbook(fileInputStream);
	
	//Get the first sheet
	Sheet sheet = workbook.getSheetAt(0);
	
	//Create a data validation object for the cell where you want to add the dropdown
	
	CellRangeAddressList cellRange=new CellRangeAddressList(0,0,4,4);
	DataValidationHelper dvHelper=sheet.getDataValidationHelper();
	DataValidationConstraint dvConstraint=dvHelper.createExplicitListConstraint(new String[] {"Rosera","Samastipur","Patna"});
	
	DataValidation validation=dvHelper.createValidation(dvConstraint, cellRange);
	//set the dropdown cell to allow list Validation
	sheet.addValidationData(validation);
	
	//Save the changes to the Excel file
	//FileOutputStream fileOutputStream=new FileOutputStream(filePath);
	ServletOutputStream outputStream = response.getOutputStream();
	workbook.write(outputStream);
	
	fileInputStream.close();
	fileInputStream.close();
	workbook.close();
	
	
	}
}
