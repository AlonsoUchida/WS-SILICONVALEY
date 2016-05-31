package com.valmar.silliconvalley.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valmar.silliconvalley.dao.TipoDao;
import com.valmar.silliconvalley.model.Tipo;
import com.valmar.silliconvalley.services.TipoService;

@Service("tipoService")
@Transactional
public class TipoServiceImpl implements TipoService{

	@Autowired
	private TipoDao tipoDao;
	
	@Override
	public List<Tipo> listarTipos() {
		return tipoDao.listarTipos();
	}

}
