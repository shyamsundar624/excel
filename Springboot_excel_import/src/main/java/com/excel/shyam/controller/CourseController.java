package com.excel.shyam.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.excel.shyam.entity.Course;
import com.excel.shyam.service.CourseService;

@RestController
public class CourseController {

	@Autowired
	private CourseService service;

	@PostMapping("/save")
	public ResponseEntity<String> saveCourse(@RequestBody Course course) {

		service.save(course);
		return ResponseEntity.ok("Record Got Save Successfully");
	}

	@GetMapping("/excel")
	public void generateExcelReport(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=courses.xls";

		response.setHeader(headerKey, headerValue);
		service.generateExcel(response);
	}
	
	@GetMapping("/sheet")
	public void GenerateSheet(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=course.xls";
		response.setHeader(headerKey, headerValue);
		service.generateSheet(response);
	}
	@GetMapping("/dropdown")
	public void addDropdown(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		String headervalue="attachment;filename=course.xls";
		response.setHeader(headerKey, headervalue);
		
		service.addDropdown(response);
	}
	
}
