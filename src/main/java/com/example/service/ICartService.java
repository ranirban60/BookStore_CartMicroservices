package com.example.service;

import java.util.List;

import com.example.dto.CartDto;
import com.example.model.CartModel;

public interface ICartService {

	 CartModel addCart(CartDto cartDto);

	    List<CartModel> findAll();

	    CartModel FindById(Long cartid);

	    String deleteById(Long userId,Long cartid);

	    CartModel updateCartData(Long cartid, CartDto cartDto);

	    CartModel changeCartQty(Long cartid, int quantity);

	    List<CartModel> getCartDetailsByUserId(Long userid);

	    List<CartModel> getCartDetailsByToken(String token);
}
