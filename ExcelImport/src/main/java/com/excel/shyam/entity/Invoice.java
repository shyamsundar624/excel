package com.excel.shyam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Double amount;
	private String number;
	private String receiveddate;
}
