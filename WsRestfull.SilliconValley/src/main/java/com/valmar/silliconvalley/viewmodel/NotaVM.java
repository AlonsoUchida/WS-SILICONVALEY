package com.valmar.silliconvalley.viewmodel;

import java.util.Date;

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
