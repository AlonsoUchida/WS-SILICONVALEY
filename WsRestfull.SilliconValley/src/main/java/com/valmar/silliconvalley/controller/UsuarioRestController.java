package com.valmar.silliconvalley.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.valmar.silliconvalley.services.UsuarioService;
import com.valmar.silliconvalley.util.Util;
import com.valmar.silliconvalley.xsecurity.model.Usuario;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

	@Autowired
	UsuarioService service;

	/*
	 * This method will list all existing audios.
	 */
	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = service.listarUsuarios();
		if (usuarios.isEmpty()) {
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerPorId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> obtenerPorId(@PathVariable("id") int id) {
		Usuario usuario = service.obtenerPorId(id);
		if (usuario == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/agregar", params = { "nombre", "empresa", "fechaNacimiento", "sexo", "contrasena",
			"correo", "cargo" }, method = RequestMethod.POST)
	public ResponseEntity<Usuario> agregar(@RequestParam("nombre") String nombre,
			@RequestParam("empresa") String empresa, @RequestParam("fechaNacimiento") String fechaNacimiento,
			@RequestParam("sexo") String sexo, @RequestParam("contrasena") String contrasena,
			@RequestParam("correo") String correo, @RequestParam("cargo") String cargo) {

		if (service.obtenerPorCorreo(correo) != null) {
			return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
		}

		Usuario usuario = new Usuario();

		usuario.setNombre(nombre);
		usuario.setEmpresa(empresa);
		usuario.setFechaNacimiento(Util.getDateFromString(fechaNacimiento));
		usuario.setSexo(sexo);
		usuario.setContrasena(contrasena);
		usuario.setCorreo(correo);
		usuario.setCargo(cargo);

		int userId = service.agregar(usuario);
		usuario.setId(userId);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> eliminar(@PathVariable("id") int id) {
		Usuario usuario = service.obtenerPorId(id);
		if (usuario == null) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		service.eliminar(id);
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}
}
