package com.valmar.silliconvalley.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.valmar.silliconvalley.dao.AbstractDao;
import com.valmar.silliconvalley.dao.AudioDao;
import com.valmar.silliconvalley.model.Audio;

@Repository("audioDao")
@EnableTransactionManagement
public class AudioDaoImpl extends AbstractDao<Integer, Audio> implements AudioDao{

	@Override
	public Audio obtenerPorId(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Audio) criteria.uniqueResult();
	}

	@Override
	public void agregar(Audio audio) {
		try {
			persist(audio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminar(int id) {
		try {
			Query query = getSession().createSQLQuery("delete from AUDIO where id = :id");
	        query.setInteger("id", id);
	        query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Audio> listarAudios() {
		try {
			Criteria criteria = createEntityCriteria();
	        return (List<Audio>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
