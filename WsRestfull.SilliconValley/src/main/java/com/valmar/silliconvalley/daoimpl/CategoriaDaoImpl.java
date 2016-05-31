package com.valmar.silliconvalley.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.valmar.silliconvalley.dao.AbstractDao;
import com.valmar.silliconvalley.dao.CategoriaDao;
import com.valmar.silliconvalley.model.Categoria;

@Repository("categoriaDao")
@EnableTransactionManagement
public class CategoriaDaoImpl extends AbstractDao<Integer, Categoria> implements CategoriaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> listarCategorias() {
		try {
			Criteria criteria = createEntityCriteria();
	        return (List<Categoria>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
