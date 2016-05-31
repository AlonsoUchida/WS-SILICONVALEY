package com.valmar.silliconvalley.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.valmar.silliconvalley.model.Nota;
import com.valmar.silliconvalley.services.NotaService;

@RestController
@RequestMapping("/nota")
public class NotaRestController {

	@Autowired
    NotaService service;
    /*
     * This method will list all existing audios.
     */
    @RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
    public ResponseEntity<List<Nota>> listarNotas() {
        List<Nota> notas = service.listarNotas();
        if(notas.isEmpty()){
            return new ResponseEntity<List<Nota>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Nota>>(notas, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/obtenerPorId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Nota> obtenerPorId(@PathVariable("id") int id) {
    	Nota nota = service.obtenerPorId(id);
        if (nota == null) {
            return new ResponseEntity<Nota>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Nota>(nota, HttpStatus.OK);
    }
 
    @RequestMapping(value = "/agregar/", method = RequestMethod.POST)
    public ResponseEntity<Void> agregar(@RequestBody Nota nota,  UriComponentsBuilder ucBuilder) {       
 
        if (service.obtenerPorId(nota.getId())!=null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        } 
        service.agregar(nota); 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/audio/{id}").buildAndExpand(nota.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Nota> eliminar(@PathVariable("id") int id) {
    	Nota nota = service.obtenerPorId(id);
        if (nota == null) {
            return new ResponseEntity<Nota>(HttpStatus.NOT_FOUND);
        } 
        service.eliminar(id);
        return new ResponseEntity<Nota>(HttpStatus.NO_CONTENT);
    }
}
