package com.valmar.silliconvalley.xsecurity.dao;

import com.valmar.silliconvalley.xsecurity.model.Usuario;

public interface TokenDao {
	public String generateToken(Usuario user);
	public boolean validateToken(String tokenId);
	public String getUsernameFromToken(String token);
}
