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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.certus.spring.models.Producto;
import com.certus.spring.models.Response;
import com.certus.spring.service.IProductoService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/producto")
@SessionAttributes("producto")
public class ProductoController {
    
    @Autowired
	@Qualifier("servicioProducto")
	private IProductoService InterfaceProducto;

    @GetMapping("/listar")
	public String ListarProducto(Model model) {

		model.addAttribute("TituloPagina", "Hola mundo");
		model.addAttribute("titulo", "Productos");
		Response<Producto> rspta = InterfaceProducto.listarProducto();

		if (rspta.getEstado()) {
			model.addAttribute("Mensaje", rspta.getMensaje());
			model.addAttribute("listita", rspta.getListData());
			return "productos";
		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores"; // pendiente los html
		}
	}

    @GetMapping("/crear")
	public String Formulario(Model model) {
		Producto producto = new Producto();

		model.addAttribute("TituloPagina", "Hola mundo");
		model.addAttribute("titulo", " - Crear Producto");
		model.addAttribute("producto", producto);

		return "form-producto";
	}

    @GetMapping("/editar/{codProducto}")
	public String EditarProducto(@PathVariable int idProducto, Model model) {

		model.addAttribute("TituloPagina", "Hola mundo");

		Response<Producto> rspta = InterfaceProducto.editarProducto(idProducto);

		model.addAttribute("titulo", "Editando el producto " + rspta.getData().getNombre());

		model.addAttribute("producto", rspta.getData());

		return "form-producto";
	}

    @GetMapping("/elimnar/{codProducto}")
	public String ElimnarProducto(@PathVariable int idProducto, Model model) {

		Response<Producto> rspta = InterfaceProducto.eliminarProducto(idProducto);

		if (rspta.getEstado()) {
			return "redirect:/Productos";
		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());

			return "errores";
		}
	}

    @PostMapping("/form_pro")
	public String creaProducto(@Valid Producto Mermelada, BindingResult result, Model model, SessionStatus sStatus) {

		if (result.hasErrors()) {
			return "form-producto";
		}

		Response<Producto> rspta = InterfaceProducto.crearProducto(Mermelada);

		if (rspta.getEstado()) {

			sStatus.setComplete();
			return "redirect:/Productos";

		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores";
		}
	}

}