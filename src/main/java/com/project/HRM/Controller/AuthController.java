package com.project.HRM.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.HRM.DTO.ApiResponse;
import com.project.HRM.DTO.LoginRequest;
import com.project.HRM.DTO.LoginResponse;
import com.project.HRM.DTO.UserDTO;
import com.project.HRM.Entity.User;
import com.project.HRM.Repository.UserRepository;
import com.project.HRM.Security.JwtHelper;
import com.project.HRM.Service.UserService;
import com.project.HRM.ServiceImpl.MyUserDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtHelper jwtHelper;

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
		this.doAuthenticate(loginRequest.getEmail(), loginRequest.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
		String token = jwtHelper.generateToken(userDetails);
		User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
				() -> new UsernameNotFoundException("User not found with email: " + loginRequest.getEmail()));
		LoginResponse response = LoginResponse.builder().id(user.getId()).token(token).email(user.getEmail())
				.fullName(user.getFullName()).phoneNo(user.getPhoneNo()).address(user.getAddress()).build();
		return new ResponseEntity<>(new ApiResponse<>("200", "User Logged Successfully", response), HttpStatus.OK);
	}

	private void doAuthenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			authenticationManager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username or password");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO) {
		try {
			UserDTO user = userService.createUser(userDTO);
			return ResponseEntity.ok(new ApiResponse<>("200", "User Created Successfully", user));
		} catch (RuntimeException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse<>("400", ex.getMessage(), null));
		}
	}

	@PostMapping("/register-admin")
	public ResponseEntity<ApiResponse<UserDTO>> createAdmin(@Valid @RequestBody UserDTO userDTO) {
		try {
			UserDTO user = userService.createAdmin(userDTO);
			return ResponseEntity.ok(new ApiResponse<>("200", "Admin Created Successfully", user));
		} catch (RuntimeException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse<>("400", ex.getMessage(), null));
		}
	}

	@PostMapping("/register-superAdmin")
	public ResponseEntity<ApiResponse<UserDTO>> createSuperAdmin(@Valid @RequestBody UserDTO userDTO) {
		try {
			UserDTO user = userService.createSuperAdmin(userDTO);
			return ResponseEntity.ok(new ApiResponse<>("200", "SuperAdmin Created Successfully", user));
		} catch (RuntimeException ex) {
			return ResponseEntity.badRequest().body(new ApiResponse<>("400", ex.getMessage(), null));
		}
	}
}
