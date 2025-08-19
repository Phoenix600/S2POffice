package com.s2p.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
class ProjectSecurityConfig
{
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(requests) -> (
						(AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).permitAll()
		);
		
		// Disabling Cross Site Request Forgery
		http.csrf(AbstractHttpConfigurer::disable);
		
		http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		
		return (SecurityFilterChain)http.build();
	}
	
	
	///  Creating Password Encoder At Framework Level
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	
	///  Adding Additional Password Checker Layer
	@Bean
	CompromisedPasswordChecker passwordChecker()
	{
		return new HaveIBeenPwnedRestApiPasswordChecker();
	}
	
}