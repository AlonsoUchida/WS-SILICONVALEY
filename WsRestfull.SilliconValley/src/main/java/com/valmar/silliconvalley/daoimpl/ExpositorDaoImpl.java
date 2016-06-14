package com.valmar.silliconvalley.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.valmar.silliconvalley.dao.AbstractDao;
import com.valmar.silliconvalley.dao.ExpositorDao;
import com.valmar.silliconvalley.model.Expositor;
import com.valmar.silliconvalley.model.Nota;
import com.valmar.silliconvalley.xsecurity.model.Usuario;


@Repository("expositorDao")
@EnableTransactionManagement
public class ExpositorDaoImpl extends AbstractDao<Integer, Expositor> implements ExpositorDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Expositor> listarExpositores() {
		try {
			List<Expositor> listaFinal = new ArrayList<>();
			
			Criteria criteria1 = createEntityCriteria();
			criteria1.add(Restrictions.ge("fechaExposicion", new Date()));
			List<Expositor> lista1 = (List<Expositor>) criteria1.list();
			
			
			Criteria criteria2 = createEntityCriteria();
			criteria2.add(Restrictions.lt("fechaExposicion", new Date()));
			List<Expositor> lista2 = (List<Expositor>) criteria2.list();
			
			listaFinal.addAll(lista1);
			listaFinal.addAll(lista2);
			
			return listaFinal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expositor obtenerPorId(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Expositor) criteria.uniqueResult();
	}

}
