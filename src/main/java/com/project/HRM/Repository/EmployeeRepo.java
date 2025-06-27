package com.project.HRM.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.HRM.Entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
