package com.duduanan.authdemo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        
//        AuthUserDetails authUser = (AuthUserDetails) authentication.getPrincipal();
        
        if(accessToken instanceof DefaultOAuth2AccessToken) {
            Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("account_id","32");
            additionalInfo.put("user_id", "11");
            
            OAuth2RefreshToken refreshToken = ((DefaultOAuth2AccessToken) accessToken).getRefreshToken();
            if(refreshToken instanceof ExpiringOAuth2RefreshToken) {
              additionalInfo.put("refresh_token_expire", ((ExpiringOAuth2RefreshToken) refreshToken).getExpiration().getTime());
            }
            
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        }
       
        return accessToken;
    }

}
