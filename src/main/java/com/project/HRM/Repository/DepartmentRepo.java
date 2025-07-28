package com.project.HRM.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.HRM.Entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long>{
	Optional<Department> findByDepartment(String department);
}
