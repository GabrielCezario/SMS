package com.pitang.Sms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
	
	@RequestMapping(method = RequestMethod.GET, path = "/hwp")
	public String hwp() {
		return "Hello World! PROFILE!";
	}

}
