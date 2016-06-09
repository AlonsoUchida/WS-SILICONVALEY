package com.valmar.silliconvalley.services;

import java.util.List;

import com.valmar.silliconvalley.model.Expositor;

public interface ExpositorService {
	Expositor obtenerPorId(int id);
	List<Expositor> listarExpositores();
}
