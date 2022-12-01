package com.certus.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.certus.spring.models.Producto;
import com.certus.spring.models.Response;
import com.certus.spring.service.IProductoService;

public class APIProductoController {
    
	@Autowired
	@Qualifier("servicioProducto")
	private IProductoService InterfaceProducto1;
	
	@GetMapping("/listar")
	public Response<Producto> listarProducto (){		
		Response<Producto> rspta = InterfaceProducto1.listarProducto();	
		return rspta;
	}
	
	@PutMapping("/editar/{id}")
	public Response<Producto> editarProducto(@RequestBody Producto pro, @PathVariable  int id){
		Response<Producto> rspta = new Response<>();
		
		Response<Producto> rsptaAux = InterfaceProducto1.editarProducto(id);
		
		if (rsptaAux.getEstado()) { //Si existe o no el personaje			
			pro.setCodProducto(rsptaAux.getData().getCodProducto());;			
			rspta = InterfaceProducto1.crearProducto(pro);
		}else {
			rspta = rsptaAux;
		}
		
		return rspta;
	}
	
	@PostMapping("/crear")
	public Response<Producto> crearProducto (@RequestBody Producto pro){
		Response<Producto> rspta =InterfaceProducto1.crearProducto(pro);
		return rspta;
	}
	
	@DeleteMapping("/eliminar/{id}")
	public Response<Producto> eliminarProducto (@PathVariable int id){		
		Response<Producto> rspta = InterfaceProducto1.eliminarProducto(id);		
		return rspta;
	}
	
}
