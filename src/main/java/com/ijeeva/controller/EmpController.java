package com.ijeeva.controller;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ijeeva.config.CustomEmpDtls;
import com.ijeeva.model.EmpDtls;
import com.ijeeva.model.EmployeeResponse;
import com.ijeeva.repository.EmpRepository;

@Controller
@RequestMapping("/employee/")
public class EmpController {
	
	@Autowired
	private EmpRepository empRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	@ModelAttribute
	private void EmpDetails(Model m, Principal p) {
		String email = p.getName();
		EmpDtls emp= empRepo.findByEmail(email);
		m.addAttribute("employee", emp);
	}
	
	@GetMapping("/")
	public String home(Authentication auth, ModelMap map)
	{
		CustomEmpDtls user = (CustomEmpDtls)auth.getPrincipal();
		EmployeeResponse res = new EmployeeResponse();
		res.setFullName(user.getfullname());
		res.setQualification(user.getQualification());
		
		map.addAttribute("Employee", res);
		return "employee/home";
	}
	
	@GetMapping("/changPass")
	public String loadChangePassword()
	{
		return "employee/change_password";
	}
	
	@PostMapping("/updatePassword")
	public String ChangePassword(Principal p,@RequestParam("oldPass")String oldPass,@RequestParam("newPass")String newpass, HttpSession session)
	{
		String email=p.getName();
		
		EmpDtls LoginEmp= empRepo.findByEmail(email);
		
		boolean f=passwordEncode.matches(oldPass, LoginEmp.getPassword());
		
		if(f)
		{
			LoginEmp.setPassword(passwordEncode.encode(newpass));
			EmpDtls updatePasswordEmp=empRepo.save(LoginEmp);
			
			if(updatePasswordEmp!=null) {
				session.setAttribute("msg", "password change Success");
			}else {
				session.setAttribute("msg", "somthing wrong on server");		
			}
				
		}else {
			session.setAttribute("msg", "old password");
			
		}
		
		
		return "redirect:/employee/ChangePassword";
	}
}
    
	
	


