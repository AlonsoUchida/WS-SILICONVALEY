package com.valmar.silliconvalley.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.valmar.silliconvalley.model.Audio;
import com.valmar.silliconvalley.services.AudioService;

@CrossOrigin
@RestController
@RequestMapping("/audio")
public class AudioRestController {

	@Autowired
    AudioService service;
    /*
     * This method will list all existing audios.
     */
    @RequestMapping(value = { "/listar" }, method = RequestMethod.GET)
    public ResponseEntity<List<Audio>> listarAudios() {
        List<Audio> audios = service.listarAudios();
        if(audios.isEmpty()){
            return new ResponseEntity<List<Audio>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Audio>>(audios, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/obtenerPorId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Audio> obtenerPorId(@PathVariable("id") int id) {
        Audio audio = service.obtenerPorId(id);
        if (audio == null) {
            return new ResponseEntity<Audio>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Audio>(audio, HttpStatus.OK);
    }
 
    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public ResponseEntity<Void> agregar(@RequestBody Audio audio,  UriComponentsBuilder ucBuilder) {       
 
        if (service.obtenerPorId(audio.getId())!=null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        } 
        service.agregar(audio); 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/audio/{id}").buildAndExpand(audio.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Audio> eliminar(@PathVariable("id") int id) {
        Audio audio = service.obtenerPorId(id);
        if (audio == null) {
            return new ResponseEntity<Audio>(HttpStatus.NOT_FOUND);
        } 
        service.eliminar(id);
        return new ResponseEntity<Audio>(HttpStatus.NO_CONTENT);
    }
}
