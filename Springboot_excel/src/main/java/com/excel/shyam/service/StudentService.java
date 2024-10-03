package com.excel.shyam.service;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excel.shyam.StudentRepository;
import com.excel.shyam.entity.Student;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}

	@Override
	public ByteArrayInputStream exportStudentData() throws IOException {
		List<Student> all = studentRepository.findAll();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Student");

		
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFColor color = new XSSFColor(new Color(204,255,204),null);
		cellStyle.setFillForegroundColor(color);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		XSSFRow headRow = sheet.createRow(0);
		String[] headerArr= {"Id","Name","Branch","College","Fees"};
	   int	headerCellIndex=0;
		for(String header:headerArr) {
		XSSFCell cell = headRow.createCell(headerCellIndex);
		cell.setCellValue(header);
		cell.setCellStyle(cellStyle);
		headerCellIndex++;
		}
		int rowIndex=1;
		for(Student s:all) {
			XSSFRow row = sheet.createRow(rowIndex);
			row.createCell(0).setCellValue(s.getId());
			row.createCell(1).setCellValue(s.getName());
			row.createCell(2).setCellValue(s.getBranch());
			row.createCell(3).setCellValue(s.getCollege());
			row.createCell(4).setCellValue(s.getFees());
			
			rowIndex++;
		}
		workbook.write(out);
		
		return new ByteArrayInputStream(out.toByteArray());
	}

}
