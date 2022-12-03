package com.certus.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.certus.spring.models.ResponseSuc;
import com.certus.spring.models.Sucursal;
import com.certus.spring.service.inteface.ISucursalService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/sucursal")
@SessionAttributes("sucursal")
public class SucursalController {
	
	@Autowired
	@Qualifier("servicioSucursal")
	private ISucursalService Interfacesucursal;
	
	@GetMapping("/listar")
	public String ListarSucursal(Model model) {
		
		model.addAttribute("TituloPagina", "Listar sucursales");
		model.addAttribute("titulo", "Listar sucursales");	
		ResponseSuc<Sucursal> rspta= Interfacesucursal.listarSucursal();

		if (rspta.getEstado()) {
			model.addAttribute("Mensaje",rspta.getMensaje());
			model.addAttribute("listita", rspta.getListData());
			return "listarSucursal";
		}else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores";
		}
	}
	
	@GetMapping("/crear")
	public String Formulario(Model model) {
		Sucursal sucursal = new Sucursal();
		
		model.addAttribute("TituloPagina", "Crear Sucursales");
		model.addAttribute("titulo","Crear Sucursales");
		model.addAttribute("sucursal", sucursal);
		model.addAttribute("titulobtn", "Enviar");
		return "formSucursal";
	}
	
	@GetMapping("/Editar/{idSucursal}")
	public String EditarSucursal(@PathVariable int idSucursal, Model model) {
		
		model.addAttribute("TituloPagina", "Editar sucursal");
		
		ResponseSuc<Sucursal> rspta= Interfacesucursal.editarSucursal(idSucursal);
		model.addAttribute("titulo","editar sucursal "+rspta.getData().getNombre());
		
		model.addAttribute("sucursal", rspta.getData());
		model.addAttribute("titulobtn", "Guardar");
		
		return "formSucursal";
	}
	
	@GetMapping("/Eliminar/{idSucursal}")
	public String EliminarPersonaje(@PathVariable int idSucursal, Model model) {
		
		ResponseSuc<Sucursal> rspta= Interfacesucursal.eliminarSucursal(idSucursal);
		
		if(rspta.getEstado()) {
			return "redirect:/sucursal/listar";
		}else {			
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores";
		}
	}
	
	@PostMapping("/form")
	public String crearSucursal(@Valid Sucursal Sucursal,
								 BindingResult result, 
								 @RequestParam("ImagenDelFormulario")MultipartFile fileRecibido,
								 Model model,
								 SessionStatus sStatus) {
		
		if(result.hasErrors()) {
			model.addAttribute("TituloPagina", "Crear sucursal");
			model.addAttribute("titulo", " - Crear Sucursal");
			model.addAttribute("titulobtn", "Enviar");
			return "formSucursal";
		}
		
		ResponseSuc<Sucursal> rspta= Interfacesucursal.crearSucursal(Sucursal,fileRecibido);
		
		if(rspta.getEstado()) {			
			sStatus.setComplete();  
			return "redirect:/sucursal/listar";
		}else {			
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores";
		}
		
	}
}
