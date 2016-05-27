package com.valmar.silliconvalley.xsecurity.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.valmar.silliconvalley.xsecurity.model.Usuario;

public interface UserService {

	public int validateUser(String username, String password);
	public String generateToken(int userId);
	public boolean validateToken(String token);
	public String getUsernameFromToken(String token);
	Usuario loadUserByUsername(String username) throws UsernameNotFoundException;
}
