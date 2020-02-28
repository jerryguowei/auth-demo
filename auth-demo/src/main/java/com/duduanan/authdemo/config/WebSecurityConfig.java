package com.duduanan.authdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.duduanan.authdemo.config.entrypoints.CustomEntryPoint;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//		auth.authenticationProvider(daoAuthenticationProvider());
//
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/h2-console/**","/error/**").permitAll().and().authorizeRequests().anyRequest()
				.authenticated().and().csrf().disable().headers().frameOptions().disable().and().httpBasic().authenticationEntryPoint(new CustomEntryPoint());
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);	
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 	
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setHideUserNotFoundExceptions(false);
		dao.setUserDetailsService(userDetailsService);
		dao.setPasswordEncoder(passwordEncoder());
		return dao;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
