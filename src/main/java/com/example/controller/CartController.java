package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CartDto;
import com.example.dto.ResponseDto;
import com.example.model.CartModel;
import com.example.service.ICartService;

@RestController
@RequestMapping("/Cart")
public class CartController {

	  @Autowired
	    ICartService cartService;

	    //add cart details
	    @PostMapping("/add")
	    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody CartDto cartDto) {
	         CartModel cart = cartService.addCart(cartDto);
	        ResponseDto responseDTO = new ResponseDto("cart details added", cart);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	    }
	    // get all cart data by findAll() method
	    @GetMapping("/getAll")
	    public ResponseEntity<ResponseDto> findAllDetail() {
	        List<CartModel> cartList = cartService.findAll();
	        ResponseDto responseDTO = new ResponseDto("** All cart List ** ", cartList);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }
	    // get cart details with cart id
	    @GetMapping("/get/{cartId}")
	    public ResponseEntity<ResponseDto> FindById(@PathVariable Long cartId) {
	    	CartModel response = cartService.FindById(cartId);
	        ResponseDto responseDto = new ResponseDto("***All Details cart list using Id***", response);
	        return new ResponseEntity<>(responseDto, HttpStatus.OK);
	    }
	    // Ability to delete cart details by id
	    @DeleteMapping("/delete/{userId}/{cartId}")
	    public ResponseEntity<ResponseDto> deleteById(@PathVariable  Long userId,@PathVariable Long cartId) {
	        cartService.deleteById(userId,cartId);
	        ResponseDto reponseDTO = new ResponseDto("**cart Data deleted successfully ** ", "deleted id " + cartId);
	        return new ResponseEntity<ResponseDto>(reponseDTO, HttpStatus.ACCEPTED);
	    }
	    // Ability to update  cart details by id
	    @PutMapping("/update/{cartid}")
	    public ResponseEntity<ResponseDto> editData(@PathVariable Long cartid, @Valid @RequestBody CartDto cartDto) {
	    	CartModel cartData = cartService.updateCartData(cartid, cartDto);
	        ResponseDto responseDTO = new ResponseDto("Updated Cart Details Successfully", cartData);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }
	    //Ability to update  quantity
	    @PostMapping("/update-qty")
	    public ResponseEntity<ResponseDto> changeBookQuantity(@RequestParam Long cartid, @RequestParam int quantity) {
	    	CartModel cart = cartService.changeCartQty(cartid, quantity);
	        ResponseDto responseDTO = new ResponseDto("Cart quantity changed successfully", cart);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }
	    //Get Cart Data by UserId
	    @GetMapping("/UserCart/{userId}")
	    public ResponseEntity<ResponseDto> getCartDataByUserID(@PathVariable Long Id){
	        List<CartModel> userCartDetails = cartService.getCartDetailsByUserId(Id);
	        ResponseDto responseDTO = new ResponseDto("Cart Details with User ID: "+Id, userCartDetails);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }
	    // Ability to get cart details by token
	    @GetMapping("/UserCartToken/{token}")
	    public ResponseEntity<ResponseDto> getCartDataByToken(@PathVariable String token){
	        List<CartModel> userCartDetails = cartService.getCartDetailsByToken(token);
	        ResponseDto responseDTO = new ResponseDto("Cart Details with User ID: "+token, userCartDetails);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }
}
