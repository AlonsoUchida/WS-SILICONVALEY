package com.valmar.silliconvalley.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.valmar.silliconvalley.dao.AbstractDao;
import com.valmar.silliconvalley.dao.TipoDao;
import com.valmar.silliconvalley.model.Tipo;


@Repository("tipoDao")
@EnableTransactionManagement
public class TipoDaoImpl extends AbstractDao<Integer, Tipo> implements TipoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Tipo> listarTipos() {
		try {
			Criteria criteria = createEntityCriteria();
	        return (List<Tipo>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
