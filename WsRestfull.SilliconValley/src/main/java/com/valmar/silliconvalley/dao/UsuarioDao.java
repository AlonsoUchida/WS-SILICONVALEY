package com.valmar.silliconvalley.dao;

import java.util.List;

import com.valmar.silliconvalley.xsecurity.model.Usuario;

public interface UsuarioDao {
	Usuario obtenerPorId(int id);
	Usuario obtenerPorCorreo(String correo);
    int agregar(Usuario usuario);     
    void eliminar(int id);    
    List<Usuario> listarUsuarios();
}
