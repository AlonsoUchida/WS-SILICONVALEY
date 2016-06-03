package com.valmar.silliconvalley.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.valmar.silliconvalley.model.Tipo;
import com.valmar.silliconvalley.services.TipoService;

@CrossOrigin
@RestController
@RequestMapping("/tipo")
public class TipoRestController {

	@Autowired
    TipoService service;
	
	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
    public ResponseEntity<List<Tipo>> listar() {
        List<Tipo> tipos = service.listarTipos();
        if(tipos.isEmpty()){
            return new ResponseEntity<List<Tipo>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Tipo>>(tipos, HttpStatus.OK);
    }
	
}
