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
@RequestMapping("/productos")
@SessionAttributes("producto")
public class ProductoController {

	@Autowired
	@Qualifier("servicioProducto")
	private IProductoService InterfaceProducto;

	@GetMapping("/listar")
	public String ListarProducto(Model model) {

		model.addAttribute("TituloPagina", "Listar productos");
		model.addAttribute("titulo", "Productos");
		Response<Producto> rspta = InterfaceProducto.listarProducto();

		if (rspta.getEstado()) {
			model.addAttribute("Mensaje", rspta.getMensaje());
			model.addAttribute("listita", rspta.getListData());
			return "listarProductos";
		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores"; // pendiente los html
		}
	}

	@GetMapping("/crear")
	public String Formulario(Model model) {
		Producto producto = new Producto();

		model.addAttribute("TituloPagina", "Crear producto");
		model.addAttribute("titulo", " - Crear Producto");
		model.addAttribute("producto", producto);
		model.addAttribute("titulobtn", "Enviar");

		return "formProductos";
	}

	@GetMapping("/editar/{codProducto}")
	public String EditarProducto(@PathVariable int codProducto, Model model) {

		model.addAttribute("TituloPagina", "Editar producto");

		Response<Producto> rspta = InterfaceProducto.editarProducto(codProducto);

		model.addAttribute("titulo", "Editando el producto " + rspta.getData().getNombre());

		model.addAttribute("producto", rspta.getData());
		model.addAttribute("titulobtn", "Guardar");

		return "formProductos";
	}

	@GetMapping("/eliminar/{codProducto}")
	public String ElimnarProducto(@PathVariable int codProducto, Model model) {

		Response<Producto> rspta = InterfaceProducto.eliminarProducto(codProducto);

		if (rspta.getEstado()) {
			return "redirect:/productos/listar";
		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());

			return "errores";
		}
	}

	@PostMapping("/form")
	public String creaProducto(@Valid Producto Mermelada, BindingResult result, Model model, SessionStatus sStatus) {

		if (result.hasErrors()) {
			model.addAttribute("TituloPagina", "Crear producto");
			model.addAttribute("titulo", " - Crear Producto");
			model.addAttribute("titulobtn", "Enviar");

			return "formProductos";
		}

		Response<Producto> rspta = InterfaceProducto.crearProducto(Mermelada);

		if (rspta.getEstado()) {

			sStatus.setComplete();
			return "redirect:/productos/listar";

		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "errores";
		}
	}

}
