package com.valmar.silliconvalley.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valmar.silliconvalley.dao.AudioDao;
import com.valmar.silliconvalley.model.Audio;
import com.valmar.silliconvalley.services.AudioService;

@Service("audioService")
@Transactional
public class AudioServiceImpl implements AudioService{
	
	@Autowired
	private AudioDao audioDao;

	@Override
	public Audio obtenerPorId(int id) {		
		return audioDao.obtenerPorId(id);
	}

	@Override
	public void agregar(Audio audio) {
		audioDao.agregar(audio);		
	}

	@Override
	public void eliminar(int id) {
		audioDao.eliminar(id);		
	}

	@Override
	public List<Audio> listarAudios() {
		return audioDao.listarAudios();
	}

}
