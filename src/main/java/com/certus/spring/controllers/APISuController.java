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
import com.certus.spring.models.ResponseSuc;
import com.certus.spring.models.Sucursal;
import com.certus.spring.models.dto.SucursalDTO;
import com.certus.spring.service.inteface.ISucursalService;


@RestController
@RequestMapping("/Sucursal")
public class APISuController {
	
	@Autowired
	@Qualifier("sucursal1")
	private ISucursalService Interfacesucursal;
	
	@GetMapping("/listar")
	public ResponseSuc<Sucursal>listarSucursal(){
		ResponseSuc<Sucursal> rspta= Interfacesucursal.listarSucursal();
		return rspta;
	}
	
	@PutMapping("/editar/{id}")
	public ResponseSuc<Sucursal> editarSucursal(@RequestBody SucursalDTO suc,@PathVariable int id){
		ResponseSuc<Sucursal> rspta= new ResponseSuc<>();
		
		ResponseSuc<Sucursal> rsptaAux = Interfacesucursal.editarSucursal(id);
		
		if (rsptaAux.getEstado()) {
			
			suc.setIdSucursal(rsptaAux.getData().getIdSucursal());
			rspta = Interfacesucursal.crearSucursalAPI(suc);
			
		}else {
			rspta = rsptaAux;
		}
		
		return rspta;
	}
	
	@PostMapping("/crear")
	public ResponseSuc<Sucursal> crearSucursal(@RequestBody SucursalDTO suc){
		
		ResponseSuc<Sucursal> rspta= Interfacesucursal.crearSucursalAPI(suc);
		return rspta;
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseSuc<Sucursal> eliminarSucursal(@PathVariable int id){
		
		ResponseSuc<Sucursal> rspta= Interfacesucursal.eliminarSucursal(id);
		return rspta;
	}

}
