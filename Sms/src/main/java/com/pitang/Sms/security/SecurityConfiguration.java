package com.pitang.Sms.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin();
		
		http.csrf().disable().authorizeRequests()
		.antMatchers("/h2-console**").permitAll()
		//.antMatchers("/user/addUser").permitAll()
		//.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers("/user**").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.and();
		
		/*.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		.addFilterBefore(new JTWAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);*/
		
	}
	
}
