package com.valmar.silliconvalley.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.valmar.silliconvalley.services.UsuarioService;
import com.valmar.silliconvalley.viewmodel.AuthenticationVM;
import com.valmar.silliconvalley.xsecurity.controller.JwtTokenUtil;
import com.valmar.silliconvalley.xsecurity.model.AuthenticationRequest;
import com.valmar.silliconvalley.xsecurity.model.AuthenticationResponse;
import com.valmar.silliconvalley.xsecurity.model.Usuario;
import com.valmar.silliconvalley.xsecurity.services.UserService;

@CrossOrigin
@RestController
public class AuthenticationRestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private UsuarioService usuarioService;

	@CrossOrigin
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestHeader("Authorization") String authorization)
			throws AuthenticationException {

		AuthenticationRequest authenticationRequest = jwtTokenUtil.getAuthenticationRequest(authorization);
		Usuario _usuario = userService.loadUserByUsername(authenticationRequest.getUsername());
		if (_usuario == null)
			return new ResponseEntity<String>("Usuario no existe", HttpStatus.NOT_FOUND);
		int userId = userService.validateUser(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		if (userId == 0)
			return new ResponseEntity<String>("Crendeciales incorrectas", HttpStatus.UNAUTHORIZED);
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		AuthenticationVM authVM = new AuthenticationVM();
		String token = userService.generateToken(userId);
		Usuario usuario = usuarioService.obtenerPorId(userId);
		authVM.setIdUsuario(usuario.getId());
		authVM.setPassword(usuario.getContrasena());
		authVM.setToken(token);
		// Return the token
		return new ResponseEntity<AuthenticationVM>(authVM, HttpStatus.OK);

	}

}
