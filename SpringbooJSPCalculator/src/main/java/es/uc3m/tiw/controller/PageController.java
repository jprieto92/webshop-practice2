package es.uc3m.tiw.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.Operands;
import es.uc3m.tiw.domains.Result;
import es.uc3m.tiw.domains.Producto;


@Controller
public class PageController {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/")
	public String main(Model model){
		
		return "webViewUser/catalogo";
	}
	
	
	// Several parameters can be joined into a ModelAttribute
	@RequestMapping("/catalogo")
	public String obtenerCatalogo(Model model, @RequestParam("operacion") String operacion){
		
		System.out.println(operacion);
		
		// Example using POST and JSON 
		List<Producto> listaDeProductos = restTemplate.getForObject("http://localhost:8020/{operacion}", List.class, operacion);
				
		model.addAttribute(listaDeProductos);
		return "webViewUser/catalogo";
	}
}
