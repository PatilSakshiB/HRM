package com.project.HRM.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.HRM.DTO.EmployeeDto;

@Service
public interface EmployeeService {

	EmployeeDto addEmployee(EmployeeDto employeeDto);
	
	EmployeeDto getEmpById(Long employeeId);
	
	List<EmployeeDto> getAllEmp();
	
	EmployeeDto updateEmp(Long employeeId,EmployeeDto updatedEmp);
	
	void deleteEmp(Long employeeId);
}
