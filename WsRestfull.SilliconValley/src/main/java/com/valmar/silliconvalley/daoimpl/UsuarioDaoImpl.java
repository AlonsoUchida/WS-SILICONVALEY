package com.valmar.silliconvalley.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.valmar.silliconvalley.dao.AbstractDao;
import com.valmar.silliconvalley.dao.UsuarioDao;
import com.valmar.silliconvalley.xsecurity.model.Usuario;

@Repository("usuarioDao")
@EnableTransactionManagement
public class UsuarioDaoImpl  extends AbstractDao<Integer, Usuario> implements UsuarioDao{

	@Override
	public Usuario obtenerPorId(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Usuario) criteria.uniqueResult();
	}

	@Override
	public int agregar(Usuario usuario) {
		try {
			persist(usuario);			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return obtenerPorCorreo(usuario.getCorreo()).getId();
	}

	@Override
	public void eliminar(int id) {
		try {
			Query query = getSession().createSQLQuery("delete from USUARIO where id = :id");
	        query.setInteger("id", id);
	        query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarUsuarios() {
		try {
			Criteria criteria = createEntityCriteria();
	        return (List<Usuario>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Usuario obtenerPorCorreo(String correo) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("correo", correo));
		return (Usuario) criteria.uniqueResult();
	}

}
