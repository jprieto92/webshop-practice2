package es.uc3m.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uc3m.tiw.categoria.domains.Categoria;
import es.uc3m.tiw.categoria.domains.CategoriaRepository;
import es.uc3m.tiw.categoria.domains.Producto;
import es.uc3m.tiw.categoria.domains.ProductoRepository;

@RestController
//La siguiente anotación es necesaria para permitir 
//que diferente dominios puedan usar este microservicio
@CrossOrigin
public class ControllerCategoria {
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	
	/******************************************************************/
	/*			    SECCIÓN GET (BÚSQUEDAS/CONSULTAS)				  */
	/******************************************************************/

//	/**
//	 * Búsqueda de todas las categorias
//	 * @return Devuelve una lista de categorias.
//	 *  */
	@RequestMapping(value="/categorias", method = RequestMethod.GET)
	public List<Categoria> categorias(){
		System.out.println("Buscar todas las categorias");
		return categoriaRepository.findAll();
	}
	
	/**
	 * Búsqueda de una categoria
	 * @return Devuelve una categoria.
	 * @param email Establece la búsqueda del usuario por dicho parámetro
	 *  */
	@RequestMapping(value="/categorias/{idCategoria}", method = RequestMethod.GET)
	public Categoria categoriaPorId(@PathVariable("idCategoria") Integer idCategoria){
		System.out.println("Busca categoria por id");
		Categoria categoria = categoriaRepository.findOne(idCategoria);
		System.out.println(idCategoria);
		if(categoria == null)throw new DataIntegrityViolationException("No existe la categoria con el id: "+idCategoria);

		return categoria;
	}
	
	/******************************************************************/
	/*			  		  SECCIÓN POST (CREAR)						  */
	/******************************************************************/

	/** 
	 * 
	 * Ejemplo: http://localhost:8050/categorias
	 * 	 * JSON
	 * {
		"idCategoria" : "1",

	 	"descripccion" : "Categoria relacionada con el tema de los deportes",

		"nombre": "Deportes"		
		}
	 * */
	@RequestMapping(value="/categorias", method = RequestMethod.POST)
	public Categoria crear(@RequestBody Categoria categoriaCrear){
		System.out.println("Crear categoria");
		Categoria categoria = new Categoria(categoriaCrear.getDescripccion(),categoriaCrear.getNombre());
		
		return categoriaRepository.save(categoria);
	}
	
	


	/******************************************************************/
	/*			  		  SECCIÓN PUT (MODIFICAR)					  */
	/******************************************************************/

	/** 
	 * 
	 * Ejemplo: http://localhost:8050/categorias
	 * 	 * JSON
	 * 
		{
		"idCategoria" : "1",

	 	"descripccion" : "Categoria relacionada con el tema de la moda",

		"nombre": "Moda"
		}
	 * @throws Exception 
	 * */
	@RequestMapping(value="/categorias", method = RequestMethod.PUT)
	public Categoria modificar(@RequestBody Categoria categoriaCrear){
		System.out.println("Modificar categoria");

		if(categoriaCrear.getIdCategoria() == 0){
			throw new DataIntegrityViolationException("Debe indicar el id de categoria");
		}

		/* Se recupera la categoria original */
		Categoria categoria = categoriaPorId(categoriaCrear.getIdCategoria());

		/* Se efectuan las modificaciones que sean necesarias. Los setters ya controlan que no sean null */
		categoria.setIdCategoria(categoriaCrear.getIdCategoria());
		categoria.setNombre(categoriaCrear.getNombre());
		categoria.setDescripccion(categoriaCrear.getDescripccion());	
		

		return categoriaRepository.save(categoria);
	}
	
	/******************************************************************/
	/*			 		  SECCIÓN DELETE (BORRAR)					  */
	/******************************************************************/

	/** 
	 * 
	 * Ejemplo: http://localhost:8050/categorias/1
	 * 
	 * @throws Exception 
	 * */
	@RequestMapping(value="/categorias/{idCategoria}", method = RequestMethod.DELETE)
	public void eliminar(@PathVariable("idCategoria") Integer idCategoria){
		System.out.println("Eliminar categoria con id: "+idCategoria);

		if(idCategoria == null){
			throw new DataIntegrityViolationException("Debe indicar un id de categoria");
		}
		List<Producto> productos= productoRepository.findByCategoriaIdCategoria(idCategoria);
		if(productos == null | productos.size() == 0){
			/* Se consulta que ese id de categoria existe. Si no existe, ya lanzará excepción el propio método  */
			categoriaPorId(idCategoria);
			
			categoriaRepository.delete(idCategoria);
		}else{
			throw new DataIntegrityViolationException("No se puede borrar la categoria porque aun tiene productos asociados");
		}
		
		
		
		
	}
}
