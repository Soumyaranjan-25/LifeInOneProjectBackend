package com.lio.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lio.serviceImpl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader= request.getHeader("Authorization");
		String userName=null;
		String jwtToken=null;
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken=requestTokenHeader.substring(7);
			try {
			userName=this.jwtutil.extractUsername(jwtToken);
			}
			catch(ExpiredJwtException eje) {
				eje.printStackTrace();
				System.out.println("Jwt token expired");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Invalid Token , not start with bearer string ");
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() ==null) {
			final UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(userName);
			if(this.jwtutil.validateToken(jwtToken, userDetails)) {
				try {
				System.out.println(userDetails.getAuthorities().toString());
				// token valid
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}catch(LazyInitializationException e){
					System.out.println("Problem in get the authorities");
					e.printStackTrace();
				}
			}
		}
		else {
			System.out.println("Token is not valid");
		}
		filterChain.doFilter(request, response);
	}

}
