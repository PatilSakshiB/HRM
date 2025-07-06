package com.project.HRM.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.HRM.DTO.EmployeeDto;

@Service
public interface EmployeeService {

	public EmployeeDto addEmployee(EmployeeDto employeeDto);
	
	public EmployeeDto getEmpById(Long employeeId);
	
	public List<EmployeeDto> getAllEmp();
}
