package com.duduanan.authdemo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import com.duduanan.authdemo.repository.ClientRepository;

public class AuthClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;
       
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientRepository.findByClientId(clientId);
    }

}
