package com.project.HRM.Service;

import org.springframework.stereotype.Service;

import com.project.HRM.DTO.UserDTO;

@Service
public interface UserService {

	UserDTO createUser(UserDTO userDTO);
}
