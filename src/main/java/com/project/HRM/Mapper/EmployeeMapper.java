package com.project.HRM.Mapper;

import com.project.HRM.DTO.EmployeeDto;
import com.project.HRM.Entity.Employee;

public class EmployeeMapper {

	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(
				employee.getId(),
				employee.getFullName(),
				employee.getPhoneNo(),
				employee.getEmail(),
				employee.getDepartment(),
				employee.getVerificationToken(),
				employee.isVerified());
	}

	public static Employee mapToEmployee(EmployeeDto employeeDto) {
		return new Employee(
				employeeDto.getId(),
				employeeDto.getFullName(),
				employeeDto.getEmail(),
				employeeDto.getPhoneNo(),
				employeeDto.getDepartment(),
				employeeDto.getVerificationToken(),
				employeeDto.isVerified());
	}
}

