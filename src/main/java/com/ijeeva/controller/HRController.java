package com.ijeeva.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/HR/")
public class HRController {
	
	@GetMapping("/")
	public String home()
	{
		return "HR/home";
		
	}
	

}
