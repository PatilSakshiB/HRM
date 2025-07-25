package com.project.HRM.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.HRM.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByRole(String role);
}
