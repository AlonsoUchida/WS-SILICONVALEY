package com.valmar.silliconvalley.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valmar.silliconvalley.dao.NotaDao;
import com.valmar.silliconvalley.model.Nota;
import com.valmar.silliconvalley.services.NotaService;

@Service("notaService")
@Transactional
public class NotaServiceImpl implements NotaService{

	@Autowired
	private NotaDao notaDao;
	
	@Override
	public Nota obtenerPorId(int id) {
		return notaDao.obtenerPorId(id);
	}

	@Override
	public void agregar(Nota nota) {
		notaDao.agregar(nota);		
	}

	@Override
	public void eliminar(int id) {
		notaDao.eliminar(id);
		
	}

	@Override
	public List<Nota> listarNotas() {
		return notaDao.listarNotas();
	}

}
