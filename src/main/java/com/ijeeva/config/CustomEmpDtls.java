package com.ijeeva.config;


import java.util.Arrays;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ijeeva.model.EmpDtls;

public class CustomEmpDtls implements UserDetails {
	
	private EmpDtls Employee;
	
	
	public CustomEmpDtls(EmpDtls employee) {
		super();
		this.Employee = employee;
	}

	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(Employee.getRole());
		return Arrays.asList(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		
		return Employee.getPassword();
	}

	@Override
	public String getUsername() {
		
		return Employee.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getfullname() {
		return Employee.getFullName();

	}
	
	public String getQualification() {
		return Employee.getQualification();
	}

}
	
	


