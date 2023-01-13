package com.example.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	Long userId;
	String firstName;
	String lastName;
	String address;
	String email_address;
	LocalDate DOB;
	String password;
}
