package com.ijeeva.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ijeeva.model.EmpDtls;
import com.ijeeva.service.EmpService;



@Controller
public class HomeController {

	@Autowired
	private EmpService EmpService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/createUser")
	public String createuser(@ModelAttribute EmpDtls user, HttpSession session) {

		// System.out.println(user);

		boolean f = EmpService.checkEmail(user.getEmail());

		if (f) {
			session.setAttribute("msg", "Email Id alreday exists");
		}

		else {
			EmpDtls userDtls = EmpService.createUser(user);
			if (userDtls != null) {
				session.setAttribute("msg", "Register Sucessfully");
			} else {
				session.setAttribute("msg", "Something wrong on server");
			}
		}

		return "redirect:/register";
	}
	

	@GetMapping("/home")
	public String home(Authentication auth, HttpSession session, ModelMap map) {
	
		
		if(auth.getAuthorities().stream()
			      .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return "redirect:/admin/";
		} else if(auth.getAuthorities().stream()
			      .anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
			return "redirect:/employee/";
		}
		return "index";
	}
		

}
