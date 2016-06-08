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
	
	

}
