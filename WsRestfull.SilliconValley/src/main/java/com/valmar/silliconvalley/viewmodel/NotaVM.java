package com.valmar.silliconvalley.viewmodel;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.valmar.silliconvalley.model.Audio;
import com.valmar.silliconvalley.model.Categoria;
import com.valmar.silliconvalley.model.Expositor;
import com.valmar.silliconvalley.model.Tipo;
import com.valmar.silliconvalley.xsecurity.model.Usuario;

public class NotaVM {

	private String comentario;
	private Date fechaRegistro;
	private int[] categorias;
	private int[] tipos;
	private int expositor_id;	
	private int usuario_id;
	
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public int[] getCategorias() {
		return categorias;
	}
	public void setCategorias(int[] categorias) {
		this.categorias = categorias;
	}
	public int[] getTipos() {
		return tipos;
	}
	public void setTipos(int[] tipos) {
		this.tipos = tipos;
	}
	public int getExpositor_id() {
		return expositor_id;
	}
	public void setExpositor_id(int expositor_id) {
		this.expositor_id = expositor_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	

}
