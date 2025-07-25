package com.project.HRM.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.HRM.DTO.ApiResponse;
import com.project.HRM.DTO.UserDTO;
import com.project.HRM.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO){
		UserDTO user=userService.createUser(userDTO);
		return ResponseEntity.ok(new ApiResponse<>("200", "User Created Successfully", user));
	}
}
