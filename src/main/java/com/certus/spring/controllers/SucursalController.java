package com.certus.spring.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
	@Qualifier("sucursal1")
	private ISucursalService Interfacesucursal;
	
	// @GetMapping({"/home","/inicio","/","/Home","/Inicio"})
	// public String Home(Model model) {
		
	// 	model.addAttribute("TituloPagina", tittlePage);
	// 	model.addAttribute("titulo", "Seccion j98 - Pagina de Inicio");		
	// 	model.addAttribute("Mensaje",mensaje);
	// 	return "Home";
	// }
	
	@GetMapping("/listar")
	public String ListarSucursal(Model model) {
		
		model.addAttribute("TituloPagina", "========");
		model.addAttribute("titulo", "========");	
		ResponseSuc<Sucursal> rspta= Interfacesucursal.listarSucursal();

		if (rspta.getEstado()) {
			model.addAttribute("Mensaje",rspta.getMensaje());
			model.addAttribute("listita", rspta.getListData());
			
			return "";
		}else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensaje", rspta.getMensajeError());
			return "";
		}
	}
	
	@GetMapping("/crear")
	public String Formulario(Model model) {
		Sucursal sucursal = new Sucursal();
		
		model.addAttribute("TituloPagina", "========");
		model.addAttribute("titulo","==============");
		model.addAttribute("sucursal", sucursal);
		return "";
	}
	
	@GetMapping("/Editar/{idSucursal}")
	public String EditarSucursal(@PathVariable int idSucursal, Model model) {
		
		model.addAttribute("TituloPagina", "========");
		
		ResponseSuc<Sucursal> rspta= Interfacesucursal.editarSucursal(idSucursal);
		model.addAttribute("titulo","editar sucursal "+rspta.getData().getNombre());
		
		model.addAttribute("sucursal", rspta.getData());
		
		return "";
	}
	
	@GetMapping("/Eliminar/{idSucursal}")
	public String EliminarPersonaje(@PathVariable int idSucursal, Model model) {
		
		ResponseSuc<Sucursal> rspta= Interfacesucursal.eliminarSucursal(idSucursal);
		
		if(rspta.getEstado()) {
			return "";
		}else {			
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensaje", rspta.getMensajeError());
			return "";
		}
	}
	
	@PostMapping("/form")
	public String crearSucursal(@Valid Sucursal Sucursal,
								 BindingResult result, 
								 @RequestParam("ImagenDelFormulario")MultipartFile fileRecibido,
								 Model model,
								 SessionStatus sStatus) {
		
		if(result.hasErrors()) {
			return "";
		}
		
		ResponseSuc<Sucursal> rspta= Interfacesucursal.crearSucursal(Sucursal,fileRecibido);
		
		if(rspta.getEstado()) {			
			sStatus.setComplete();  
			return "";
		}else {			
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensaje", rspta.getMensajeError());
			return "";
		}
		
	}
}
