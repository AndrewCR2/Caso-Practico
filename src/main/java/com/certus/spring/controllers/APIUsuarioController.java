package com.certus.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.certus.spring.models.Response;
import com.certus.spring.models.Usuario;
import com.certus.spring.service.IUsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class APIUsuarioController {


	@Autowired
	@Qualifier("servicioUsuario")
	private IUsuarioService InterfaceUsuario;
	
	@GetMapping("/listar")
	public Response<Usuario> listarUsuario (){		
		Response<Usuario> rspta = InterfaceUsuario.listarUsuario();	
		return rspta;
	}
	
	@PutMapping("/editar/{id}")
	public Response<Usuario> editarUsuario(@RequestBody Usuario pro, @PathVariable  int id){
		Response<Usuario> rspta = new Response<>();
		
		Response<Usuario> rsptaAux = InterfaceUsuario.editarUsuario(id);
		
		if (rsptaAux.getEstado()) { //Si existe o no el personaje			
			pro.setId(rsptaAux.getData().getId());;			
			rspta = InterfaceUsuario.crearUsuario(pro);
		}else {
			rspta = rsptaAux;
		}
		
		return rspta;
	}
	
	@PostMapping("/crear")
	public Response<Usuario> crearUsuario (@RequestBody Usuario pro){
		Response<Usuario> rspta =InterfaceUsuario.crearUsuario(pro);
		return rspta;
	}
	
	@DeleteMapping("/eliminar/{id}")
	public Response<Usuario> eliminarUsuario (@PathVariable int id){		
		Response<Usuario> rspta = InterfaceUsuario.eliminarUsuario(id);		
		return rspta;
	}
}
