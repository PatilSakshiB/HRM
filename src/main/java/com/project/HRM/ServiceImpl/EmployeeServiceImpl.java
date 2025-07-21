package com.project.HRM.ServiceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.HRM.DTO.EmployeeDto;
import com.project.HRM.Entity.Employee;
import com.project.HRM.Exception.ResourceNotFoundException;
import com.project.HRM.Mapper.EmployeeMapper;
import com.project.HRM.Repository.EmployeeRepo;
import com.project.HRM.Service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepo employeeRepo;
	
	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		String token = UUID.randomUUID().toString();
        employee.setVerificationToken(token);
        employee.setVerified(false);
        Employee savedEmp = employeeRepo.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmp);
	}

	@Override
	public EmployeeDto getEmpById(Long employeeId) {
		Employee employee = employeeRepo.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee not found with given id "+employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmp() {
		List<Employee> employees=employeeRepo.findAll();
		return employees.stream().
				map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmp(Long employeeId, EmployeeDto updatedEmp) {
		Employee employee= employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with given Id: "+employeeId));
		employee.setFullName(updatedEmp.getFullName());
		employee.setEmail(updatedEmp.getEmail());
		employee.setPhoneNo(updatedEmp.getPhoneNo());
		employee.setDepartment(updatedEmp.getDepartment());
		Employee updatedemployee = employeeRepo.save(employee);
		return EmployeeMapper.mapToEmployeeDto(updatedemployee);
	}

	@Override
	public void deleteEmp(Long employeeId) {
		Employee employee= employeeRepo.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found with given Id: "+employeeId));
		employeeRepo.delete(employee);
	}

}
