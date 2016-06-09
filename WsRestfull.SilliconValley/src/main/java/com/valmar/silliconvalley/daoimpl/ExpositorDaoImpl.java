package com.valmar.silliconvalley.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
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
			Criteria criteria = createEntityCriteria();
	        return (List<Expositor>) criteria.list();
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
