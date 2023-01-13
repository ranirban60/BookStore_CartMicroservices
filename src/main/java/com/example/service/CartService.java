package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dto.BookDto;
import com.example.dto.CartDto;
import com.example.dto.UserDto;
import com.example.exception.CartException;
import com.example.model.CartModel;
import com.example.repository.ICartRepo;
import com.example.utility.EmailSenderService;
import com.example.utility.JwtTokenUtil;

@Service
public class CartService implements ICartService{

	 @Autowired
	    ICartRepo cartRepo;
	    @Autowired
	    JwtTokenUtil tokenUtil;
	    @Autowired
	    RestTemplate restTemplate;
	    @Autowired
	    EmailSenderService emailSenderService;

	    @Override
	    public CartModel addCart(CartDto cartDto)  {
	        UserDto userData = restTemplate.getForObject("http://localhost:8081/User/Get/" + cartDto.getUserId(), UserDto.class);
//	        ResponseEntity<UserDto> userDetails = restTemplate.getForEntity("http://localhost:8081/User/Get/"+cartDto.getUserid(), UserDto.class);
	//

	        //Converting to the JSON object
//	        JSONObject user = new JSONObject(userData);
	        //printing User details in JSON Object form
//	        System.out.println(user);
//	        System.out.println(user.get("email_address"));

	        BookDto bookDetails = restTemplate.getForObject("http://localhost:8082/Book/get/" + cartDto.getBookId(), BookDto.class);
	        if (userData!=null && bookDetails!=null) {
	        	CartModel cartDetails = new CartModel(cartDto);
	            return cartRepo.save(cartDetails);
	        } else {
	            throw new CartException("invalid user Id and BookID");

	        }

	    }

	    @Override
	    public List<CartModel> findAll() {
	        List<CartModel> cartdetails  = cartRepo.findAll();
	        return cartdetails;
	    }
	    // finding the cart details using cart id
	    @Override
	    public CartModel FindById(Long cartid) {
	    	CartModel cart = cartRepo.findById(cartid).orElse(null);
	        if (cart != null) {
	            return cart;

	        }else
	            throw new CartException("card id is not found");
	    }

	    @Override
	    public String deleteById(Long userId,Long cartid) {
	    	CartModel findById = cartRepo.findById(cartid).orElse(null);
	        UserDto userData = restTemplate.getForObject("http://localhost:8081/User/Get/" + userId, UserDto.class);
	        if (findById != null&& userData!=null) {
	           if(userData.getUserId().equals(findById.getUserId())) {
	               cartRepo.deleteById(cartid);
	               return "data is deleted";
	           }else
	               throw new CartException("userid does not match");
	        } else throw new CartException("id is invalid");
	    }

	    @Override
	    public CartModel updateCartData(Long id, CartDto cartDto) {
	        UserDto userData = restTemplate.getForObject("http://localhost:8081/User/Get/" + cartDto.getUserId(), UserDto.class);

	        BookDto bookDetails = restTemplate.getForObject("http://localhost:8082/Book/get/" + cartDto.getBookId(), BookDto.class);

	        CartModel editdata = cartRepo.findById(id).orElse(null);
	        if (userData!=null&&bookDetails!=null&&editdata!=null) {
	            editdata.setBookId(cartDto.getBookId());
	            editdata.setUserId(cartDto.getUserId());
	            editdata.setQuantity(cartDto.getQuantity());
	            return cartRepo.save(editdata);

	        }else
	            throw new CartException(" id is invalid");
	    }
	    
	    @Override
	    public CartModel changeCartQty(Long cartid, int quantity) {
	    	CartModel cart = cartRepo.findById(cartid).orElse(null);
	        if(cart == null){
	            throw new CartException("id is not found");
	        }
	        cart.setQuantity(quantity);
	        return cartRepo.save(cart);
	    }

	    @Override
	    public List<CartModel> getCartDetailsByUserId(Long userid) {
	        List<CartModel> userCartList = cartRepo.getCartListWithUserId(userid);
	        if(userCartList.isEmpty()){
	            throw new CartException("Cart is Empty!");
	        }else
	            return userCartList;
	    }
	    @Override
	    public List<CartModel> getCartDetailsByToken(String token) {
	        Long userId = tokenUtil.decodeToken(token);
	        List<CartModel> userCartList = cartRepo.getCartListWithUserId(userId);
	        if(userCartList.isEmpty()){
	            throw new CartException("Cart is Empty!");
	        }else
	            return userCartList;
	    }
}
