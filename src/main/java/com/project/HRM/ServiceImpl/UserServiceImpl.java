package com.project.HRM.ServiceImpl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.HRM.DTO.UserDTO;
import com.project.HRM.Entity.Role;
import com.project.HRM.Entity.User;
import com.project.HRM.Repository.RoleRepository;
import com.project.HRM.Repository.UserRepository;
import com.project.HRM.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user=mapper.map(userDTO, User.class);
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		Role role=roleRepository.findByRole("ROLE_USER").orElseGet(() ->{
			Role newRole=new Role();
			newRole.setRole("ROLE_USER");
			return roleRepository.save(newRole);
		});
		user.setRole(role);
		 String token = UUID.randomUUID().toString();
	        user.setVerificationToken(token);
	        user.setVerified(false);

	        User saved = this.userRepository.save(user);
		
		return mapper.map(saved, UserDTO.class);
	}

}
