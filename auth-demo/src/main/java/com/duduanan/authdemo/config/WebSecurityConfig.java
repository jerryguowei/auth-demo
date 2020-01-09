package com.duduanan.authdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
       .and().authorizeRequests().anyRequest().authenticated().and().formLogin()
       .and().csrf().disable().headers().frameOptions().disable();
    }

    
}
