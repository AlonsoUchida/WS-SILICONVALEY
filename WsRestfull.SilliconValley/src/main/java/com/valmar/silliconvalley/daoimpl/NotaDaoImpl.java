package com.valmar.silliconvalley.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.valmar.silliconvalley.dao.AbstractDao;
import com.valmar.silliconvalley.dao.NotaDao;
import com.valmar.silliconvalley.model.Nota;
import com.valmar.silliconvalley.util.Util;

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
			Query query1 = getSession().createSQLQuery("delete from TIPOXNOTA where id_nota = :id");
			query1.setInteger("id", id);
			query1.executeUpdate();
			
			Query query2 = getSession().createSQLQuery("delete from CATEGORIAXNOTA where id_nota = :id");
			query2.setInteger("id", id);
			query2.executeUpdate();
			
			Query query3 = getSession().createSQLQuery("delete from NOTA where id = :id");
			query3.setInteger("id", id);
			query3.executeUpdate();
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
		int firstElement;
		if(page == 1)
			firstElement = page - 1;
		else 
			firstElement = (pageSize * (page - 1));
				
		criteria.setFirstResult(firstElement);
		criteria.setMaxResults(pageSize);
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
		criteria.add(Restrictions.eq("expositor.id", id));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("n.id"))
				.add(Projections.property("n.comentario"))
				.add(Projections.property("n.fechaRegistro"))
				.add(Projections.property("expositor.id"))
				.add(Projections.property("usuario.id"))
				.add(Projections.groupProperty("n.id"))
				);
		criteria.addOrder(Order.desc("n.id"));
		
		List<Object[]> results = criteria.list();
		List<Nota> notas = new ArrayList<>();
		for (Object[] row : results) {
			Nota nota = new Nota();
			nota.setId(Integer.parseInt(row[0].toString()));
			nota.setComentario(row[1].toString());
			nota.setFechaRegistro(Util.getDateFromStringSecondFormat(row[2].toString()));
			notas.add(nota);
		}

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
			distOr.add(Restrictions.eq("usuario.id", usuarioId));
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
