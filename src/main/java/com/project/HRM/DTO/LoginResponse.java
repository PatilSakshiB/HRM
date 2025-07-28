package com.project.HRM.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginResponse {

	private Long id;
	private  String token ;
	private String fullName;
	private String email;
	private String phoneNo;
	private String address;
	
}
