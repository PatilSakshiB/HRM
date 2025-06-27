package com.project.HRM.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private Long id;
	private String fullName;
	private String email;
	private String phoneNo;
	private String department;
}
