package com.valmar.silliconvalley.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.valmar.silliconvalley.xsecurity.model.Usuario;
import com.valmar.silliconvalley.model.Expositor;

@Entity
@Table(name="NOTA")
public class Nota {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "COMENTARIO")
	private String comentario;
	
	@Column(name = "FECHA_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "nota", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<Audio> audios;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
	      name="CATEGORIAXNOTA",
	      joinColumns=@JoinColumn(name="ID_NOTA", referencedColumnName="ID"),
	      inverseJoinColumns=@JoinColumn(name="ID_CATEGORIA", referencedColumnName="ID"))
	@JsonManagedReference
	private Set<Categoria> categorias;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	  @JoinTable(
	      name="TIPOXNOTA",
	      joinColumns=@JoinColumn(name="ID_NOTA", referencedColumnName="ID"),
	      inverseJoinColumns=@JoinColumn(name="ID_TIPO", referencedColumnName="ID"))
	@JsonManagedReference
	private Set<Tipo> tipos;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_EXPOSITOR")
	@JsonManagedReference
	private Expositor expositor;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_USUARIO")
	@JsonManagedReference
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

	public Set<Audio> getAudios() {
		return audios;
	}

	public void setAudios(Set<Audio> audios) {
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

	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Set<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(Set<Tipo> tipos) {
		this.tipos = tipos;
	}
	
}
