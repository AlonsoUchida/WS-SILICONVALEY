package com.valmar.silliconvalley.xsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valmar.silliconvalley.xsecurity.dao.UserDao;
import com.valmar.silliconvalley.xsecurity.model.JwtUserFactory;
import com.valmar.silliconvalley.xsecurity.model.Usuario;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
