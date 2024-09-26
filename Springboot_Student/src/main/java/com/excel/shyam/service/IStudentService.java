package com.excel.shyam.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface IStudentService {

	public void saveStudent();
	
	public ByteArrayInputStream exportStudentData() throws IOException;
}
