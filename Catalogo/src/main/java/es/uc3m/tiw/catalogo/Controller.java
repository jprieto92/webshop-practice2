package es.uc3m.tiw.catalogo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.uc3m.tiw.catalogo.domains.Producto;
import es.uc3m.tiw.catalogo.domains.ProductoDAO;
import es.uc3m.tiw.catalogo.domains.Count;


@RestController
// The following annotation is necessary to allow 
// different domains to be able to use this microservice
// La siguiente anotación es necesaria para permitir 
// que diferente dominios puedan usar este microservicio
@CrossOrigin
public class Controller {

	@Autowired
	ProductoDAO productoDAO;
	
	/**
	 * Devuelve una lista de productos en JSON
	 *  */
	@RequestMapping(value="productos", method = RequestMethod.GET)
	public List<Producto> productos(){
		System.out.println("Buscar todos los productos");
		return productoDAO.findAll();
	}

	@RequestMapping(value="productosCount", method=RequestMethod.GET)
	public Count cantidad(){
		System.out.println("Cantidad Elementos");
		return new Count(productoDAO.count());
	}
	
	@RequestMapping(value="productosBusqueda", method=RequestMethod.GET)
	public List<Producto> buscarPorNombre(@RequestParam(value="id", required=true) int id){
		System.out.println("Buscar productos por id");
		return productoDAO.findByProductId(id);
	}
}