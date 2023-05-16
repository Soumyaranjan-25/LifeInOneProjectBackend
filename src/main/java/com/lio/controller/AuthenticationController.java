package com.lio.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lio.config.JwtUtil;
import com.lio.dto.JwtRequest;
import com.lio.dto.JwtResponse;
import com.lio.model.User;
import com.lio.serviceImpl.UserDetailsServiceImpl;


@RestController
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@ModelAttribute JwtRequest jwtRequest) throws Exception {
		try {
			authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("user not found");
		}
		UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());
		String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String userName, String password) throws Exception {
		try {
//			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
//			 Authentication authentication = authenticationManager.authenticate(
//	                    new UsernamePasswordAuthenticationToken(userName, password)
//	            );
//			 System.out.println(authentication.getPrincipal());
		} catch (DisabledException e) {
			throw new Exception("User Disabled " + e.getMessage());
		} catch (BadCredentialsException e) {
			throw new Exception("User credentials are worng" + e.getMessage());
		}
	}
	
	// return the details of login user
		@GetMapping("/current-user")
		public User getCurrent(Principal principal) {
			System.out.println("Comming here");
			return (User) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
		}
}
