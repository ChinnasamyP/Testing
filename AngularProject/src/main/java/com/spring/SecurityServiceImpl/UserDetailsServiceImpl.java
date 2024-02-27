package com.spring.SecurityServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.SecurityEntity.User;
import com.spring.SecurityRepository.UserRepository;
import com.spring.SecurityService.UserDetailsService;

@Service
public class UserDetailsServiceImpl   {
//
//	
//	
//	@Autowired
//	UserRepository userRepository;
//	
//	
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		User user = userRepository.findByUsername(username).orElseThrow(()-> new  UsernameNotFoundException("User Not Found eith username :"+username));
//		return UserDetailsImpl.build(user);
//	}
//
}
