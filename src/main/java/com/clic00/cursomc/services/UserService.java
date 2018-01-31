package com.clic00.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.clic00.cursomc.security.UserSS;

@Service
public class UserService {

	public static UserSS authenticated() {

		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} 
		catch (Exception e) {
			return null;
		}
	}
}