package com.valmar.silliconvalley.services;

import java.util.List;

import com.valmar.silliconvalley.model.Audio;

public interface AudioService {
	List<Audio> listarPorNota(int id);	 
	Audio obtenerPorId(int id);	 
    void agregar(Audio audio);     
    void eliminar(int id);    
    List<Audio> listarAudios();
}
