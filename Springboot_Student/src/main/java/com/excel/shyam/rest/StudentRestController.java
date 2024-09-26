package com.excel.shyam.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excel.shyam.service.IStudentService;

@RestController
@RequestMapping("/students")
public class StudentRestController {

	@Autowired
	private IStudentService service;

	@GetMapping("/export")
	public ResponseEntity<byte[]> exportStudentData() throws IOException {

		ByteArrayInputStream inputStream = service.exportStudentData();
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=student.xlsx";

		HttpHeaders headers = new HttpHeaders();
		headers.add(headerKey, headerValue);

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(inputStream.readAllBytes());
	}
}
