package com.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {
	Long userId;
	Long bookId;
	int quantity;
}
