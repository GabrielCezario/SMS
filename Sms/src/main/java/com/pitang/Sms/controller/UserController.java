package com.pitang.Sms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.Sms.dto.UserDto;
import com.pitang.Sms.mapper.ModelMapperComponent;
import com.pitang.Sms.model.UserModel;
import com.pitang.Sms.service.UserService;

@RestController
public class UserController {
	
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/hwu" )
	public String hwu() {
		return "Hello World! USER!";
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

}
