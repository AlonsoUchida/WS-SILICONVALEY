package com.valmar.silliconvalley.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Reporte {
	
	private int id;
	private String comentario;
	private Date fechaRegistro;
	private List<Audio> audios;
	private List<Categoria> categorias;
	private List<Tipo> tipos;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public List<Audio> getAudios() {
		return audios;
	}
	public void setAudios(List<Audio> audios) {
		this.audios = audios;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	public List<Tipo> getTipos() {
		return tipos;
	}
	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}
	
	

}
