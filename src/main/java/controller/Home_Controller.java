package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")

public class Home_Controller {

    @Value("${title.generic}")
    private String titlePage;

    @Autowired
    @Qualifier("")
  //  private IProductoService InterfaceProducto;

    @GetMapping({ "/home", "inicio", "/", "Home", "Inicio" })
    public String HolaMundo(Model model) {
        model.addAttribute("TituloPagina", titlePage);

        return "index";
    }

}
