package com.valmar.silliconvalley.dao;

import java.util.List;

import com.valmar.silliconvalley.model.Expositor;
import com.valmar.silliconvalley.model.Nota;

public interface ExpositorDao {
	Expositor obtenerPorId(int id);	 
	List<Expositor> listarExpositores();
}
