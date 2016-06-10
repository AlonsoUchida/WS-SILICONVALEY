package com.valmar.silliconvalley.services;

import java.util.List;

import com.valmar.silliconvalley.xsecurity.model.Usuario;

public interface UsuarioService {
	Usuario obtenerPorId(int id);	 
    int agregar(Usuario usuario);     
    void eliminar(int id);    
    List<Usuario> listarUsuarios();
    Usuario obtenerPorCorreo(String correo);
}
