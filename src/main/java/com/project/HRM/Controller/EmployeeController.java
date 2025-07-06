package com.project.HRM.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.HRM.DTO.EmployeeDto;
import com.project.HRM.Service.EmployeeService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

	private EmployeeService employeeService;
	
	@PostMapping("/add")
	public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto employee = employeeService.addEmployee(employeeDto);
		return new ResponseEntity<>(employee,HttpStatus.CREATED);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<EmployeeDto> getEmpById(@PathVariable("id") Long employeeId){
		EmployeeDto employee = employeeService.getEmpById(employeeId);
		return ResponseEntity.ok(employee);		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
		List<EmployeeDto> empList = employeeService.getAllEmp();
		return ResponseEntity.ok(empList);
	}
	
	@PutMapping("/updateEmp/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,@RequestBody EmployeeDto updatedEmp){
		EmployeeDto employee = employeeService.updateEmp(employeeId, updatedEmp);
		return ResponseEntity.ok(employee);
	}
	
	@DeleteMapping("/deleteEmp/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
		employeeService.deleteEmp(employeeId);
		return ResponseEntity.ok("Employee deleted successfully");
	}
}
