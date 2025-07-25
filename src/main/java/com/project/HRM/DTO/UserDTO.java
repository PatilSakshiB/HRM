package com.project.HRM.DTO;

import com.project.HRM.Entity.Department;
import com.project.HRM.Entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private String fullName;
	private String email;
	private String password;
	private String phoneNo;
	private String address;
	private Role role;
	private Department department;
}
