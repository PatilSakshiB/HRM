package com.project.HRM.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.HRM.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
