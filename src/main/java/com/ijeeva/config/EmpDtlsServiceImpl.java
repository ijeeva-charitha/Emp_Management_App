package com.ijeeva.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ijeeva.model.EmpDtls;
import com.ijeeva.repository.EmpRepository;

@Service
public class EmpDtlsServiceImpl implements EmpDetailsService, UserDetailsService {

	@Autowired
	private EmpRepository empRepo;

	
	public CustomEmpDtls loadUserByUsername(String email) throws UsernameNotFoundException {

		EmpDtls Employee = empRepo.findByEmail(email);

		if (Employee != null) {
			return new CustomEmpDtls(Employee);
		}

		throw new UsernameNotFoundException("user not available");
	}

}