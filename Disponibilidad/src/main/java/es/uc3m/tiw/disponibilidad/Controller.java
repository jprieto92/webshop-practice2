package es.uc3m.tiw.disponibilidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uc3m.tiw.disponibilidad.domains.Disponibilidad;
import es.uc3m.tiw.disponibilidad.domains.DisponibilidadRepository;
import es.uc3m.tiw.disponibilidad.domains.ProductoRepository;



@RestController
//La siguiente anotación es necesaria para permitir 
//que diferente dominios puedan usar este microservicio
@CrossOrigin
public class Controller {
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	DisponibilidadRepository disponibilidadRepository;
	
	/******************************************************************/
	/*			    SECCIÓN GET (BÚSQUEDAS/CONSULTAS)				  */
	/******************************************************************/

	/**
	 * Búsqueda de todas las disponibilidades
	 * @return Devuelve una lista de disponibilidades.
	 *  */
	@RequestMapping(value="/disponibilidades", method = RequestMethod.GET)
	public List<Disponibilidad> disponibilidades(){
		System.out.println("Buscar todas las disponibilidades");
		return disponibilidadRepository.findAll();
	}
	
	/**
	 * Búsqueda de una disponibilidad
	 * @return Devuelve una disponibilidad.
	 * @param email Establece la búsqueda de la disponibilidad por dicho parámetro
	 *  */
	@RequestMapping(value="/disponibilidades/{idDisponibilidad}", method = RequestMethod.GET)
	public Disponibilidad disponibilidadPorId(@PathVariable("idDisponibilidad") Integer idDisponibilidad){
		System.out.println("Busca categoria por id");
		Disponibilidad disponibilidad = disponibilidadRepository.findOne(idDisponibilidad);
		System.out.println(idDisponibilidad);
		if(disponibilidad == null)throw new DataIntegrityViolationException("No existe la disponibilidad con el id: "+idDisponibilidad);

		return disponibilidad;
	}
	
	/******************************************************************/
	/*			  		  SECCIÓN POST (CREAR)						  */
	/******************************************************************/

	@RequestMapping(value="/disponibilidades", method = RequestMethod.POST)
	public Disponibilidad crear(@RequestBody Disponibilidad disponibilidadCrear){
		System.out.println("Crear disponibilidad");
		Disponibilidad disponibilidad = new Disponibilidad(disponibilidadCrear.getNombre(), disponibilidadCrear.getDescripccion());
		
		return disponibilidadRepository.save(disponibilidad);
	}
	
	


	/******************************************************************/
	/*			  		  SECCIÓN PUT (MODIFICAR)					  */
	/******************************************************************/

	@RequestMapping(value="/disponibilidades", method = RequestMethod.PUT)
	public Disponibilidad modificar(@RequestBody Disponibilidad disponibilidadCrear){
		System.out.println("Modificar disponibilidad");

		if(disponibilidadCrear.getIdDisponibilidad() == 0){
			throw new DataIntegrityViolationException("Debe indicar el id de disponibilidad");
		}

		/* Se recupera la categoria original */
		Disponibilidad disponibilidad = disponibilidadPorId(disponibilidadCrear.getIdDisponibilidad());

		/* Se efectuan las modificaciones que sean necesarias. Los setters ya controlan que no sean null */
		disponibilidad.setIdDisponibilidad(disponibilidadCrear.getIdDisponibilidad());
		disponibilidad.setNombre(disponibilidadCrear.getNombre());
		disponibilidad.setDescripccion(disponibilidadCrear.getDescripccion());	
		

		return disponibilidadRepository.save(disponibilidad);
	}
	
	/******************************************************************/
	/*			 		  SECCIÓN DELETE (BORRAR)					  */
	/******************************************************************/

	@RequestMapping(value="/disponibilidades/{idDisponibilidad}", method = RequestMethod.DELETE)
	public void eliminar(@PathVariable("idDisponibilidad") Integer idDisponibilidad){
		System.out.println("Eliminar disponibilidad con id: "+idDisponibilidad);

		if(idDisponibilidad == null){
			throw new DataIntegrityViolationException("Debe indicar un id de disponibilidad");
		}
		List<Disponibilidad> productosConDisponibilidadABorrar = productoRepository.findByDisponibilidadIdDisponibilidad(idDisponibilidad);
		if(productosConDisponibilidadABorrar == null | productosConDisponibilidadABorrar.size() == 0){
			/* Se consulta que ese id de disponibilidad existe. Si no existe, ya lanzará excepción el propio método  */
			disponibilidadPorId(idDisponibilidad);
			
			disponibilidadRepository.delete(idDisponibilidad);
		}else{
			throw new DataIntegrityViolationException("No se puede borrar la disponibilidad porque aun tiene productos asociados");
		}
		
		
		
		
	}
}