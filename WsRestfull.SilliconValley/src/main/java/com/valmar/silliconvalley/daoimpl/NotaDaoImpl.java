package com.valmar.silliconvalley.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
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
			merge(nota);
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
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Nota> notas = (List<Nota>) criteria.list();
			return notas;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Nota> obtenerPorExpositor(int id, int take, int skip, int page, int pageSize) {
		Criteria criteria = getSession().createCriteria(Nota.class, "n");
		int firstElement, finalElement;
		if(page == 0)
			firstElement = page;
		else 
			firstElement = (pageSize * (page - 1));
		
		finalElement = firstElement + pageSize;
		
		criteria.setFirstResult(firstElement);
		criteria.setMaxResults(finalElement);
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
		criteria.add(Restrictions.eq("expositor.id", id));
		List<Nota> notas = (List<Nota>) criteria.list();
		return notas;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Nota> obtenerReporte(Integer[] tiposId, Integer[] categId, Integer usuarioId, Integer expositorId) {
		Criteria criteria = getSession().createCriteria(Nota.class, "n");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Disjunction distOr = Restrictions.disjunction();
		if(tiposId[0]!=null){
			criteria.createAlias("n.tipos", "t");
			Disjunction restOr = Restrictions.disjunction();
			for(int id : tiposId){
				restOr.add(Restrictions.eq("c.id", id));
			}
			distOr.add(restOr);
		}		
		if(categId[0]!=null){
			criteria.createAlias("n.categorias", "c");
			Disjunction restOr = Restrictions.disjunction();
			for(int id : categId){
				restOr.add(Restrictions.eq("c.id", id));
			}
			distOr.add(restOr);			
		}
		if(usuarioId!=null)
			distOr.add(Restrictions.eq("usuario.id", new Long(usuarioId)));
		if(expositorId!=null)
			distOr.add(Restrictions.eq("expositor.id", expositorId));
		criteria.add(distOr);

		List<Nota> notas = (List<Nota>) criteria.list();
		return notas;
	}

	@Override
	public void actualizar(Nota nota) {
		try {
			merge(nota);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
