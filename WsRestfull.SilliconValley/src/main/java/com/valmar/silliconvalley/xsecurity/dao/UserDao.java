package com.valmar.silliconvalley.xsecurity.dao;

import com.valmar.silliconvalley.xsecurity.model.Usuario;

public interface UserDao {	 
	public int validateUser(String username, String password);	
	public Usuario getUserById(int userId);
	public Usuario findByUsername(String username);
	
}
