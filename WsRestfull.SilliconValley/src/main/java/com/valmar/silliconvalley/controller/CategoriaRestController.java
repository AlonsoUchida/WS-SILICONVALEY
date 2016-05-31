package com.valmar.silliconvalley.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.valmar.silliconvalley.model.Categoria;
import com.valmar.silliconvalley.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaRestController {
	
	@Autowired
    CategoriaService service;
	
	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = service.listarCategorias();
        if(categorias.isEmpty()){
            return new ResponseEntity<List<Categoria>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Categoria>>(categorias, HttpStatus.OK);
    }

}
