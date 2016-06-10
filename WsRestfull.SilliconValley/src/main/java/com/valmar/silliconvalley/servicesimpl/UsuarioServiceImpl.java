package com.valmar.silliconvalley.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valmar.silliconvalley.dao.UsuarioDao;
import com.valmar.silliconvalley.services.UsuarioService;
import com.valmar.silliconvalley.xsecurity.model.Usuario;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioDao usuarioDao;
	
	@Override
	public Usuario obtenerPorId(int id) {
		return usuarioDao.obtenerPorId(id);
	}

	@Override
	public int agregar(Usuario usuario) {
		return usuarioDao.agregar(usuario);	
	}

	@Override
	public void eliminar(int id) {
		usuarioDao.eliminar(id);
		
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioDao.listarUsuarios();
	}

	@Override
	public Usuario obtenerPorCorreo(String correo) {
		return usuarioDao.obtenerPorCorreo(correo);
	}

}
