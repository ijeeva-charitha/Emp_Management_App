package com.ijeeva.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ijeeva.repository.EmpRepository;
@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	
	@Autowired
	public AuthenticationSuccessHandler customSuccessHandler;
	@Autowired
	private EmpRepository empRepo;
	
	@Bean
	public EmpDtlsServiceImpl getEmpDetailsService() {
		return new EmpDtlsServiceImpl();
	}
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider getDaoAuthProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getEmpDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return daoAuthenticationProvider;
	}
	
	
	/*protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getDaoAuthProvider());
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/user/**").hasRole("USER")
				.antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").loginProcessingUrl("/login")
				.defaultSuccessUrl("/user/").and().csrf().disable();       */
	
		
		@Bean
		    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			  http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/user/**").hasRole("USER")
			  .antMatchers("/HR/**").access("hasRole('ROLE_HR')")
			  .antMatchers("/**").permitAll().and().
			  formLogin().loginPage("/signin").loginProcessingUrl("/login").defaultSuccessUrl("/home")
				.and().csrf().disable();
			 
			  http.authenticationProvider(getDaoAuthProvider());
			  
		        return http.build();
		   
		        			
	}
}