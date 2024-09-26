package com.excel.shyam.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.excel.shyam.entity.Student;
import com.excel.shyam.repo.StudentRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

	private final StudentRepository repo;

	@PostConstruct
	@Override
	public void saveStudent() {
		// TODO Auto-generated method stub
		List<Student> list = List.of(new Student("shyam", "CS", "Axis", 3432),
				new Student("sangam", "CS", "Axis", 3432), new Student("Navaya", "CS", "Axis", 3432));

		repo.saveAll(list);
	}

	@Override
	public ByteArrayInputStream exportStudentData() throws IOException {
		List<Student> all = repo.findAll();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Student");

		XSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("Id");
		headRow.createCell(1).setCellValue("Name");
		headRow.createCell(2).setCellValue("Branch");
		headRow.createCell(3).setCellValue("College");
		headRow.createCell(4).setCellValue("Fees");

		int rowIndex = 1;

		for (Student s : all) {
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
