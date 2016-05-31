package com.valmar.silliconvalley.dao;

import java.util.List;

import com.valmar.silliconvalley.model.Nota;

public interface NotaDao {
	Nota obtenerPorId(int id);	 
    void agregar(Nota nota);     
    void eliminar(int id);    
    List<Nota> listarNotas();
}
