package com.pitang.Sms.service.impl;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pitang.Sms.model.UserModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {
	
	//EXPIRATION_TIME = 10 DIAS;
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET_KEY = "MySecret";
	static final String TOKEN_PREFIX = "bearer";
	static final String HEADER_STRING = "Authorization";
	
	public String generateToken(UserModel userModel) {
		
		String JWT = Jwts.builder()
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject("teste JWT")
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
		
		return JWT;
	}
	
	static void addAuthentication(HttpServletResponse response, String userName) {
		
		String JWT = Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}
	
	static Authentication getAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			
			String user = Jwts.parser().setSigningKey(SECRET_KEY)
						  .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
						  .getBody().getSubject();
			
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, Collections.emptyList());
			}
			
		}
		
		return null;
	}
	
}
