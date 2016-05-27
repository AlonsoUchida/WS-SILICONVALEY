package com.valmar.silliconvalley.xsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valmar.silliconvalley.xsecurity.dao.TokenDao;
import com.valmar.silliconvalley.xsecurity.dao.UserDao;
import com.valmar.silliconvalley.xsecurity.model.Usuario;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TokenDao tokenDao;

	public int validateUser(String username, String password) {
		return userDao.validateUser(username, password);
	}

	public String generateToken(int userId) {
		Usuario user = userDao.getUserById(userId);
		if(user!=null)
			return tokenDao.generateToken(user);
		else 
			return null;
	}
	
	public boolean validateToken(String token){
		return tokenDao.validateToken(token);
	}
	
	public String getUsernameFromToken(String token){
		return tokenDao.getUsernameFromToken(token);
	}
	
	@Override
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario = userDao.findByUsername(username);
        if (usuario == null) {
            return null;
        } else {
            return usuario;
        }
    }

}
