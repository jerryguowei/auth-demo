package com.duduanan.authdemo.repository;

import org.springframework.data.repository.Repository;

import com.duduanan.authdemo.entity.User;

public interface UserRepository extends Repository<User, Integer> {
	
	User findByEmail(String email);

}
