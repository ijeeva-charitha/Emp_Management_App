package com.ijeeva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijeeva.model.EmpDtls;

public interface EmpRepository extends JpaRepository<EmpDtls, Integer> {

	public boolean existsByEmail(String email);

	public EmpDtls findByEmail(String email);

}

