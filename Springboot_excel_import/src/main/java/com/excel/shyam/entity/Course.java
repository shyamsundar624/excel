package com.excel.shyam.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="course")
@Getter
@Setter
public class Course {
	@Id
private Integer cid;
private String cName;
private double cPrice;



}
