package com.valmar.silliconvalley.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.valmar.silliconvalley.dao.AbstractDao;
import com.valmar.silliconvalley.dao.NotaDao;
import com.valmar.silliconvalley.model.Nota;


@Repository("notaDao")
@EnableTransactionManagement
public class NotaDaoImpl extends AbstractDao<Integer, Nota> implements NotaDao {

	@Override
	public Nota obtenerPorId(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Nota) criteria.uniqueResult();
	}

	@Override
	public void agregar(Nota nota) {
		try {
			persist(nota);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminar(int id) {
		try {
			Query query = getSession().createSQLQuery("delete from NOTA where id = :id");
	        query.setInteger("id", id);
	        query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Nota> listarNotas() {
		try {
			Criteria criteria = createEntityCriteria();
	        return (List<Nota>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
