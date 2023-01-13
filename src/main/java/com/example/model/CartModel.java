package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.dto.CartDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cartId", nullable = false)
	Long cartId;
	Long userId;
	Long bookId;
	int quantity;

	// parameterized constructor
	public CartModel(CartDto cartDto) {
		this.userId = cartDto.getUserId();
		this.bookId = cartDto.getBookId();
		this.quantity = cartDto.getQuantity();
	}
}
