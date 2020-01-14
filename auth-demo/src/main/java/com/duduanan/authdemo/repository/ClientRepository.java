package com.duduanan.authdemo.repository;

import org.springframework.data.repository.Repository;

import com.duduanan.authdemo.entity.Client;


public interface ClientRepository extends Repository<Client, String> {
	
	Client findByClientId(String clientId);

}
