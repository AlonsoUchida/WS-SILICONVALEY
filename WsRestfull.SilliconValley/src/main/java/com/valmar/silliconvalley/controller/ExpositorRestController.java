package com.valmar.silliconvalley.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.valmar.silliconvalley.model.Expositor;
import com.valmar.silliconvalley.services.ExpositorService;

@CrossOrigin
@RestController
@RequestMapping("/expositor")
public class ExpositorRestController {

	@Autowired
    ExpositorService service;
	
	@RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
    public ResponseEntity<List<Expositor>> listar() {
        List<Expositor> expositores = service.listarExpositores();
        if(expositores.isEmpty()){
            return new ResponseEntity<List<Expositor>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Expositor>>(expositores, HttpStatus.OK);
    }
}
