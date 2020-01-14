package com.duduanan.authdemo.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

@Entity
public class Client  implements ClientDetails {
    /**
     * 
     */
    private static final long serialVersionUID = -6253398835215550698L;
    @Id
    private String clientId;
    private String secret;
    private String scopes;
    private String authorities;
    private String clientRedirectUris;
    private String authorizedGrantTypes;
    private String resourceIds;
    private int acessTokenValiditySeconds;
    private int refreshTokenValiditySecods;
    
    
    @Override
    public String getClientId() {
        return clientId;
    }
    @Override
    public Set<String> getResourceIds() {
        return resourceIds != null ? new HashSet<>(Arrays.asList(resourceIds.split(","))) : new HashSet<>();
    }
    @Override
    public boolean isSecretRequired() {
        return true;
    }
    @Override
    public String getClientSecret() {
        return secret;
    }
    
    @Override
    public boolean isScoped() {
        return false;
    }
    @Override
    public Set<String> getScope() {
        return scopes != null ? new HashSet<>(Arrays.asList(scopes.split(","))) : new HashSet<>();
    }
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes != null ? new HashSet<>(Arrays.asList(authorizedGrantTypes.split(","))) : new HashSet<>();
    }
    @Override
    public Set<String> getRegisteredRedirectUri() {
        return clientRedirectUris != null ? new HashSet<>(Arrays.asList(clientRedirectUris.split(","))) : new HashSet<>();
    }
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities != null ? new HashSet<>(Arrays.asList(authorities.split(",")).stream().map((name)-> new SimpleGrantedAuthority(name)).collect(Collectors.toList())) : new HashSet<>();
    }
    @Override
    public Integer getAccessTokenValiditySeconds() {
        return acessTokenValiditySeconds;
    }
    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySecods;
    }
    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
    
    
    
    
    
    
}
