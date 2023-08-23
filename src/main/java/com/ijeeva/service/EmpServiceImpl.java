package com.ijeeva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ijeeva.model.EmpDtls;
import com.ijeeva.repository.EmpRepository;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpRepository EmpRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Override
	public EmpDtls createUser(EmpDtls Employee) {

		Employee.setPassword(passwordEncode.encode(Employee.getPassword()));
		Employee.setRole("ROLE_USER");

		return EmpRepo.save(Employee);
	}

	@Override
	public boolean checkEmail(String email) {

		return EmpRepo.existsByEmail(email);
	}

}