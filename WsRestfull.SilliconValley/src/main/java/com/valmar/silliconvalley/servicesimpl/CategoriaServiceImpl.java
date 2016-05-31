package com.valmar.silliconvalley.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valmar.silliconvalley.dao.CategoriaDao;
import com.valmar.silliconvalley.model.Categoria;
import com.valmar.silliconvalley.services.CategoriaService;

@Service("categoriaService")
@Transactional
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	private CategoriaDao categoriaDao;
	
	@Override
	public List<Categoria> listarCategorias() {
		return categoriaDao.listarCategorias();
	}

}
