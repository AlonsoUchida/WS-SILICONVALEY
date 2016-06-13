package com.valmar.silliconvalley.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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

import com.valmar.silliconvalley.model.Categoria;
import com.valmar.silliconvalley.model.Expositor;
import com.valmar.silliconvalley.model.Nota;
import com.valmar.silliconvalley.model.Tipo;
import com.valmar.silliconvalley.services.CategoriaService;
import com.valmar.silliconvalley.services.ExpositorService;
import com.valmar.silliconvalley.services.NotaService;
import com.valmar.silliconvalley.services.TipoService;
import com.valmar.silliconvalley.util.Util;
import com.valmar.silliconvalley.viewmodel.NotaVM;
import com.valmar.silliconvalley.xsecurity.model.Usuario;
import com.valmar.silliconvalley.xsecurity.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/nota")
public class NotaRestController {

	@Autowired
    NotaService service;
	
	@Autowired
    CategoriaService categoriaService;
	
	@Autowired
	TipoService tipoService;
	
	@Autowired
	ExpositorService expositorService;
	
	@Autowired
	UserService userService;
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

    @RequestMapping(value = "/agregar", params= {"comentario" , "fechaRegistro", "categorias", "tipos", "expositor_id", "usuario_id"},  method = RequestMethod.POST)
    public ResponseEntity<Void> agregar(@RequestParam("comentario") String  comentario,
    		@RequestParam("fechaRegistro") String  fechaRegistro,
    		@RequestParam("categorias") Integer[]  categorias,
    		@RequestParam("tipos") Integer[]  tipos,
    		@RequestParam("expositor_id") Integer  expositor_id,
    		@RequestParam("usuario_id") Integer  usuario_id
    		,UriComponentsBuilder ucBuilder) {        
        Nota nota = new Nota();
        nota.setComentario(comentario);
        nota.setFechaRegistro(Util.getDateFromString(fechaRegistro));
        
        List<Categoria> _categorias = new ArrayList<>();
        List<Tipo> _tipos = new ArrayList<>();
        for(int id : categorias){
        	Categoria caterogia = categoriaService.listarCategorias()
        			.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        	_categorias.add(caterogia);
        }
        nota.setCategorias(new HashSet<Categoria>(_categorias));
        for(int id : tipos){
        	Tipo tipo = tipoService.listarTipos()
        			.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        	_tipos.add(tipo);
        }
        nota.setCategorias(new HashSet<Categoria>(_categorias));
        nota.setTipos(new HashSet<Tipo>(_tipos));
        
        Expositor expositor = expositorService.obtenerPorId(expositor_id);
        if(expositor==null)
        	return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);       
        nota.setExpositor(expositor);
              
        Usuario usuario = userService.getUserById(usuario_id);
        if(usuario==null)
        	return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        nota.setUsuario(usuario);

        service.agregar(nota); 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/nota/{id}").buildAndExpand(nota.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
    public ResponseEntity<Void> actualizar(@RequestBody NotaVM notaVM,  UriComponentsBuilder ucBuilder) {        
        Nota nota = new Nota();
        nota.setId(notaVM.getId());
        nota.setComentario(notaVM.getComentario());
        nota.setFechaRegistro(notaVM.getFechaRegistro());
        
        List<Categoria> categorias = new ArrayList<>();
        List<Tipo> tipos = new ArrayList<>();
        for(int id : notaVM.getCategorias()){
        	Categoria caterogia = categoriaService.listarCategorias()
        			.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        	categorias.add(caterogia);
        }
        nota.setCategorias(new HashSet<Categoria>(categorias));
        for(int id : notaVM.getTipos()){
        	Tipo tipo = tipoService.listarTipos()
        			.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        	tipos.add(tipo);
        }
        nota.setCategorias(new HashSet<Categoria>(categorias));
        nota.setTipos(new HashSet<Tipo>(tipos));
        
        Expositor expositor = expositorService.obtenerPorId(notaVM.getExpositor_id());
        nota.setExpositor(expositor);
              
        Usuario usuario = userService.getUserById(notaVM.getUsuario_id());
        nota.setUsuario(usuario);

        service.actualizar(nota); 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/nota/{id}").buildAndExpand(nota.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
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
    
    @RequestMapping(value = "/obtenerReporte", params= {"tiposId" , "categId", "usuarioId", "expositorId" },  method = RequestMethod.GET)
    public ResponseEntity<List<Nota>> obtenerReporte(@RequestParam("tiposId") Integer[] tiposId,
    		@RequestParam("categId") Integer[] categId,
    		@RequestParam("usuarioId") Integer  usuarioId,
    		@RequestParam("expositorId") Integer  expositorId) {
    	List<Nota> notas = service.obtenerReporte(tiposId, categId, usuarioId, expositorId);
    	if(notas.isEmpty()){
            return new ResponseEntity<List<Nota>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Nota>>(notas, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/obtenerPorExpositor", params= {"id", "take", "skip", "page", "pageSize"},  method = RequestMethod.GET)
    public ResponseEntity<List<Nota>> obtenerPorExpositor(@RequestParam("id") Integer id,
    		@RequestParam("take") Integer take,
    		@RequestParam("skip") Integer skip,
    		@RequestParam("page") Integer page,
    		@RequestParam("pageSize") Integer pageSize) {
    	List<Nota> notas = service.obtenerPorExpositor(id, take, skip, page, pageSize);
    	if(notas.isEmpty()){
            return new ResponseEntity<List<Nota>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Nota>>(notas, HttpStatus.OK);
    }
}
