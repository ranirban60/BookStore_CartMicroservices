package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.CartModel;

@Repository
public interface ICartRepo extends JpaRepository<CartModel, Long>{

	 @Query(value = "SELECT * FROM cart WHERE userid=:id", nativeQuery = true)
	    List<CartModel> getCartListWithUserId(Long id);
}
