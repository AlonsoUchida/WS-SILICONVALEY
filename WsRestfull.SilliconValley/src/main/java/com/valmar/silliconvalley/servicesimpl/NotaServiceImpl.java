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

	@Override
	public List<Nota> obtenerReporte(Integer[] tiposId, Integer[] categId, Integer usuarioId, Integer expositorId) {
		return notaDao.obtenerReporte(tiposId, categId, usuarioId, expositorId);
	}

	@Override
	public List<Nota> obtenerPorExpositor(int id, int take, int skip, int page, int pageSize){
		return notaDao.obtenerPorExpositor(id, take, skip, page, pageSize);
	}

	@Override
	public void actualizar(Nota nota) {
		notaDao.actualizar(nota);		
	}

}
