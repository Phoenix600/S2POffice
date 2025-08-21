package com.s2p.config;

import com.s2p.exceptionHandling.CustomBasicAuthenticationEntryPoint;
import com.s2p.exceptionHandling.CustomerAccessDeniedHandler;
import com.s2p.filters.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
class ProjectSecurityConfig
{
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("*"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }));

                http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new RequestBeforeValidation(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLogginAtFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure());// Only HTTPS


                http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/v1/admin/**").authenticated()
                .requestMatchers("/api/v1/attendance/**").authenticated()
                .requestMatchers("/api/v1/batch/**").authenticated()
                .requestMatchers("/api/v1/course/**").authenticated()
                .requestMatchers("/api/v1/topic/**").authenticated()
                .requestMatchers("/api/v1/student/**").authenticated()
                .requestMatchers("/api/v1/teacher/**").authenticated()
                .requestMatchers("/api/v1/assessment/**").authenticated()
                .requestMatchers("/api/v1/public/**").permitAll()
                .requestMatchers("/swagger-ui/index.html#").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                .requestMatchers("/notices", "/contact", "/error", "/register", "/invalidSession", "/apiLogin").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomerAccessDeniedHandler()));

                return http.build();

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