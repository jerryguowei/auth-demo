package com.duduanan.authdemo.config;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.duduanan.authdemo.config.entrypoints.CustomEntryPoint;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private ResourceLoader resourceLoader;

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
	
	@Bean
	public JwtAccessTokenConverter tokenEnhancer() throws IOException{
		Resource resource = resourceLoader.getResource("classpath:privateKey");
		
		PrivateKey privateKey = loadPrivateKey(resource.getFile());
		Resource resource1 = resourceLoader.getResource("classpath:certificate.pem");
		PublicKey publicKey = loadCertificate(resource1.getFile()).getPublicKey();
		
		KeyPair keyPair = new KeyPair(publicKey, privateKey);
		
	    final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
	    
	    jwtAccessTokenConverter.setKeyPair(keyPair);
	    return jwtAccessTokenConverter;
	}
	
	 /**
     * Load a PKCS8 key
     * @param filename The path of the key
     */
    private PrivateKey loadPrivateKey(File file) {

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] keyArray = new byte[fis.available()];
            fis.read(keyArray);
            keyArray = java.util.Base64.getDecoder().decode(keyArray);
            PKCS8EncodedKeySpec kspec = new PKCS8EncodedKeySpec(keyArray);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(kspec);
        } catch (Exception e) {
            // throw new SamlException("Couldn't load private key", e);
        }
        return null;
    }

    /**
     * Load an X.509 certificate
     * @param filename The path of the certificate
     */
    private X509Certificate loadCertificate(File file) {
        try (FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis)) {

            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            return (X509Certificate) cf.generateCertificate(bis);

        } catch (FileNotFoundException e) {
            // throw new SamlException("Public key file doesn't exist", e);
        } catch (Exception e) {
            // throw new SamlException("Couldn't load public key", e);
        }
        return null;
    }
}
