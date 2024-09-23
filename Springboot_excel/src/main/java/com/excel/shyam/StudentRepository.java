package com.excel.shyam;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excel.shyam.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

}
