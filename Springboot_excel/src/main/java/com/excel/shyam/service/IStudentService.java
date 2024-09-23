package com.excel.shyam.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.excel.shyam.entity.Student;

public interface IStudentService {

	public Student saveStudent(Student student);
	
	public ByteArrayInputStream exportStudentData() throws IOException;
	
}
