package com.excel.shyam.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excel.shyam.entity.Student;
import com.excel.shyam.service.IStudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
@Autowired
	private IStudentService studentService;
	
	@PostMapping("/save")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student ){
	return ResponseEntity.status(HttpStatus.CREATED).body(studentService.saveStudent(student));
	}
	
	public ResponseEntity<byte[]> exportStudentData() throws IOException{
		ByteArrayInputStream inputStream = studentService.exportStudentData();
		
		String headerKey="Content-Disposition";
		String headerValue="attachment: filename=student.xlsx";
		HttpHeaders headers=new HttpHeaders();
		headers.add(headerKey, headerValue);
		
		return ResponseEntity.ok().headers(headers).
				contentType(MediaType.