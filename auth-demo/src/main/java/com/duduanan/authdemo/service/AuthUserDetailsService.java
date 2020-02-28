package com.duduanan.authdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.duduanan.authdemo.entity.AuthUserDetails;
import com.duduanan.authdemo.entity.User;
import com.duduanan.authdemo.repository.UserRepository;

@Service
public class AuthUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	private static Logger logger = LoggerFactory.getLogger(AuthUserDetailsService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		
		if(user != null) {
			UserDetails userDetails = new AuthUserDetails(user);
			return userDetails;
		}
		logger.info("not found user for username - " + username);
		throw new UsernameNotFoundException("user name is not found.");
	}
}
