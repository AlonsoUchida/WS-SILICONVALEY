package com.valmar.silliconvalley.controller;

import java.util.List;

import javax.persistence.Column;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.valmar.silliconvalley.model.Audio;
import com.valmar.silliconvalley.model.Nota;
import com.valmar.silliconvalley.services.AudioService;
import com.valmar.silliconvalley.services.NotaService;

@CrossOrigin
@RestController
@RequestMapping("/audio")
public class AudioRestController {

	@Autowired
    AudioService service;
	@Autowired
	NotaService notaService;
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
    
    @RequestMapping(value = "/listarPorNota/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Audio>> listarPorNota(@PathVariable("id") int id) {
    	List<Audio> audios = service.listarPorNota(id);
        if (audios == null) {
            return new ResponseEntity<List<Audio>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Audio>>(audios, HttpStatus.OK);
    }
 
    @RequestMapping(value = "/agregar", params= {"audio" , "notaId" },  method = RequestMethod.POST)
    public ResponseEntity<Void> agregar(@RequestParam("audio") String  audio,
    		@RequestParam("notaId") Integer  notaId,
    		UriComponentsBuilder ucBuilder) {       
    	Audio audioBean = new Audio();
    	audioBean.setAudio(audio);
    	Nota nota = notaService.obtenerPorId(notaId);
    	if(nota==null){
    		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    	}
    	audioBean.setNota(nota);
        service.agregar(audioBean); 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/audio/{audio}").buildAndExpand(audioBean.getAudio()).toUri());
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
