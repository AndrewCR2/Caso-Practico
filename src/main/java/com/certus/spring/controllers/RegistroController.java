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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.certus.spring.models.Response;
import com.certus.spring.models.Usuario;
import com.certus.spring.service.IUsuarioService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/usuario")
@SessionAttributes("usuario")
public class RegistroController {

	@Value("${title.registro}")
	private String titlePage;

	@Autowired
	@Qualifier("servicioUsuario")
	private IUsuarioService InterfaceUsuario;

	@GetMapping({ "/registro" })
	public String Registro(Model model) {
		
		String tituloForm = "Registrate ahora";
		model.addAttribute("TituloPagina", titlePage);
		model.addAttribute("tituloForm",tituloForm);

		return "formUsuario";
	}

	@GetMapping("/listar")
    public String ListarUsuario(Model model) {
		model.addAttribute("tituloPagina", "LISTADO");
		
    	
    	
    	Response<Usuario> response = InterfaceUsuario.listarUsuario();
    	
    	if(response.getEstado()) {
    		model.addAttribute("titulo", "LISTADO DE USUARIOS REGISTRADOS 😎");
    		model.addAttribute("listita", response.getListData());
    		
    		return "listarUsuarios";
    		
    	}else {
    		
    		return "errores";
    	}
    	
    	
    	
    }
	
	@PostMapping("/form")
	public String crearUsuario(@Valid Usuario user,Model model,BindingResult result, SessionStatus sStatus) {
		if(result.hasErrors()) {
	    	
    		return "formUsuario";
    		
    	}
    	
    	
    	Response<Usuario> respuesta = InterfaceUsuario.crearUsuario(user);
    	
    	
    	if (respuesta.getEstado()) {
    		sStatus.setComplete();
    		return "listarUsuarios";
    		
		}else {
			model.addAttribute("mensaje", respuesta.getMensaje());
			model.addAttribute("mensajeError", respuesta.getMensaje());
			return "errores";
		}
	}

	@GetMapping("/crear")
	public String Formulario(Model model) {

		
		String tituloForm = "Registrate ahora";
		model.addAttribute("TituloPagina", titlePage);
		model.addAttribute("tituloForm", tituloForm);

		Usuario usuario = new Usuario();

		model.addAttribute("usuario", usuario);

		return "formUsuarios";

	}

	@GetMapping("/Editar/{idUsuario}")
	public String EditarUsuario(@PathVariable int idUsuario, Model model) {
		

	
		model.addAttribute("TituloPagina", "Actualizar");
		
		
		Response<Usuario> respuesta = InterfaceUsuario.editarUsuario(idUsuario);
		model.addAttribute("tituloForm", "Editar el Usuario 🗒️" + respuesta.getData().getNombre());
		
		model.addAttribute("usuario", respuesta.getData());
		

		return "formUsuarios";
	}
    
    @GetMapping("/Eliminar/{idUsuario}")
	public String EliminarPersonaje(@PathVariable int idUsuario, Model model) {
		
		Response<Usuario> respuesta = InterfaceUsuario.eliminarUsuario(idUsuario);
		if (respuesta.getEstado()) {
			return "redirect:/usuario/listar";

		} else {
			model.addAttribute("mensaje", respuesta.getMensaje());
			model.addAttribute("mensajeError", respuesta.getMensajeError());
			return "errores";
		}
	}
    
}
