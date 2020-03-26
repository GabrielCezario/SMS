package com.pitang.Sms.controller;

import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.Sms.dto.UserDto;
import com.pitang.Sms.mapper.ModelMapperComponent;
import com.pitang.Sms.model.UserModel;
import com.pitang.Sms.service.UserService;
import com.pitang.Sms.service.impl.TokenAuthenticationService;

@RestController
@RequestMapping("user")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/hwu" )
	public String hwu() {
		return "Hello World! USER!";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/login/{email}/{password}")
	public ResponseEntity<?> login(@PathVariable("email") String email, @PathVariable("password") String password){
		
		UserModel userModel = userService.findUserByEmail(email);
		
		if (email.equalsIgnoreCase(userModel.getEmail()) && password.equalsIgnoreCase(userModel.getPassword())) {
			
			String idString = String.valueOf(userModel.getId());
			String jwt = TokenAuthenticationService.generateToken(idString);
			
			System.out.println("JWt: " + jwt);
			
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/addUser")
	public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
		UserModel userModel = ModelMapperComponent.modelMapper.map(userDto, UserModel.class);
		ModelMapperComponent.modelMapper.validate();
		
		userService.addUser(userModel);
		
		userDto = ModelMapperComponent.modelMapper.map(userModel, UserDto.class);
		ModelMapperComponent.modelMapper.validate();
				
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/listAll")
	public ResponseEntity<?> getAllUser(){
		
		List<UserModel> listUserModel = userService.listUsers();
		
		if (listUserModel.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		//List<UserDto> listUserDto = ModelMapperComponent.modelMapper.map(listUserModel, UserDto.class);
		List<UserDto> listUserDto = ModelMapperComponent.modelMapper.map(listUserModel, new TypeToken<List<UserDto>>() {}.getType());
		ModelMapperComponent.modelMapper.validate();
		
		return new ResponseEntity<>(listUserDto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/listUser/{userName}")
	public ResponseEntity<?> listUser(@PathVariable("userName") String userName){
		
		if (userName.isEmpty() || userName == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		UserModel userModel = userService.findUserByName(userName);
		UserDto userDto = ModelMapperComponent.modelMapper.map(userModel, UserDto.class);
		ModelMapperComponent.modelMapper.validate();
		
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/updateUser/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto) {
		
		if (id <= 0 || userDto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		UserModel userModel = ModelMapperComponent.modelMapper.map(userDto, UserModel.class);
		ModelMapperComponent.modelMapper.validate();
		
		userService.updateUser(id, userModel);
		
		userDto = ModelMapperComponent.modelMapper.map(userModel, UserDto.class);
		ModelMapperComponent.modelMapper.validate();
		
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/deleteUser/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") long id){
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/loginUser")
	public ResponseEntity<?> loginUser(@RequestBody String login, @RequestBody String password){		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
