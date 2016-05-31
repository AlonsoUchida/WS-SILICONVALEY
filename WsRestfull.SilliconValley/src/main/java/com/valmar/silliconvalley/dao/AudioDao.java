package com.valmar.silliconvalley.dao;

import java.util.List;

import com.valmar.silliconvalley.model.Audio;

public interface AudioDao {
	Audio obtenerPorId(int id);	 
    void agregar(Audio audio);     
    void eliminar(int id);    
    List<Audio> listarAudios();
}
