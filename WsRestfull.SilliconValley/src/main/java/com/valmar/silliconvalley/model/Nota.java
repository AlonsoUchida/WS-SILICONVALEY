package com.valmar.silliconvalley.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.valmar.silliconvalley.xsecurity.model.Usuario;

@Entity
@Table(name="NOTA")
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 3, max = 1000)
	@Column(name = "COMENTARIO", nullable = false)
	private String comentario;
	
	@Column(name = "FECHA_REGISTRO", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	
	@OneToMany(mappedBy="nota")
	private List<Audio> audios;
	
	@ManyToMany
	@JoinTable(
	      name="CATEGORIAXNOTA",
	      joinColumns=@JoinColumn(name="ID_CATEGORIA", referencedColumnName="ID"),
	      inverseJoinColumns=@JoinColumn(name="ID_NOTA", referencedColumnName="ID"))
	  private List<Categoria> categorias;
	
	@ManyToMany
	  @JoinTable(
	      name="TIPOXNOTA",
	      joinColumns=@JoinColumn(name="ID_TIPO", referencedColumnName="ID"),
	      inverseJoinColumns=@JoinColumn(name="ID_NOTA", referencedColumnName="ID"))
	private List<Tipo> tipos;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_EXPOSITOR")
	private Expositor expositor;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;

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

	public Expositor getExpositor() {
		return expositor;
	}

	public void setExpositor(Expositor expositor) {
		this.expositor = expositor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
