package com.pitang.Sms.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin();
		
		http.csrf().disable().authorizeRequests()
		.antMatchers("/h2-console**").permitAll()
		.antMatchers("/user**").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic();
	}
	
}
