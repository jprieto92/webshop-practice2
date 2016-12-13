package es.uc3m.tiw.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.uc3m.tiw.usuario.domains.CategoriaRepository;
import es.uc3m.tiw.usuario.domains.DisponibilidadRepository;
import es.uc3m.tiw.usuario.domains.Producto;
import es.uc3m.tiw.usuario.domains.ProductoRepository;
import es.uc3m.tiw.usuario.domains.TipoUsuarioRepository;
import es.uc3m.tiw.usuario.domains.Usuario;
import es.uc3m.tiw.usuario.domains.UsuarioCrear;
import es.uc3m.tiw.usuario.domains.UsuarioRepository;



@RestController
//The following annotation is necessary to allow 
//different domains to be able to use this microservice
//La siguiente anotación es necesaria para permitir 
//que diferente dominios puedan usar este microservicio
@CrossOrigin
public class Controller {
	
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	DisponibilidadRepository disponibilidadRepository;
	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;
	@Autowired
	UsuarioRepository usuarioRepository;

	/******************************************************************/
	/*			    SECCIÓN GET (BÚSQUEDAS/CONSULTAS)				  */
	/******************************************************************/

//	/**
//	 * Búsqueda de todos los usuarios
//	 * @return Devuelve una lista de usuarios.
//	 *  */
//	@RequestMapping(value="/usuarios", method = RequestMethod.GET)
//	public List<Usuario> usuarios(){
//		System.out.println("Buscar todos los Usuarios");
//		return usuarioRepository.findAll();
//	}

	/**
	 * Búsqueda de un usuario
	 * @return Devuelve un usuario.
	 * @param email Establece la búsqueda del usuario por dicho parámetro
	 *  */
	@RequestMapping(value="/usuarios/{email}", method = RequestMethod.GET)
	public Usuario usuarioPorEmail(@PathVariable("idProducto") String email){
		System.out.println("Busca usuario por id");
		Usuario usuario = usuarioRepository.findOne(email);
		System.out.println(usuario);
		if(usuario==null)throw new DataIntegrityViolationException("No existe el usuario con el id: "+email);

		return usuario;
	}
	
	/******************************************************************/
	/*			  		  SECCIÓN POST (CREAR)						  */
	/******************************************************************/

	/** 
	 * 
	 * Ejemplo: http://localhost:8010/usuarios
	 * 	 * JSON
	 * {
		"email": 100@mail.com",
		"nombre": "Perico",
		"apellido1": "Palotes",
		"apellido2": "Fresa",
		"ciudad": "Madrid",
		"telefono": "987654321",
		"fechaAlta": "2054-10-20",
		"contraseña": "123456",
		}
	 * */
	@RequestMapping(value="/usuarios", method = RequestMethod.POST)
	public Usuario crear(@RequestBody UsuarioCrear usuarioCrear){

		Usuario usuario = new Usuario(usuarioCrear.getEmail(), usuarioCrear.getNombre(), usuarioCrear.getApellido1(), usuarioCrear.getApellido2(), usuarioCrear.getCiudad(), usuarioCrear.getTelefono(), usuarioCrear.getFechaAlta(),usuarioCrear.getContraseña(), null);
		
		/* Se establece la disponibilidad del producto, por defecto será 'Disponible' */
		usuario.setTipoUsuario(tipoUsuarioRepository.findOne(1));
		

		System.out.println("Almacenar usuario");

		Usuario usuario2 = usuarioRepository.save(usuario);

		System.out.println(usuario2.getEmail());
		return usuario2;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public Usuario login(@RequestBody UsuarioCrear usuarioCrear){

		Usuario usuario = new Usuario(usuarioCrear.getEmail(), usuarioCrear.getNombre(), usuarioCrear.getApellido1(), usuarioCrear.getApellido2(), usuarioCrear.getCiudad(), usuarioCrear.getTelefono(), usuarioCrear.getFechaAlta(),usuarioCrear.getContraseña(), null);
//		
//		/* Se establece la disponibilidad del producto, por defecto será 'Disponible' */
//		usuario.setTipoUsuario(tipoUsuarioRepository.findOne(1));
//		
//
//		System.out.println("Almacenar usuario");
//
//		Usuario usuario2 = usuarioRepository.save(usuario);
//
//		System.out.println(usuario2.getEmail());
		return usuario;
	}
	
	


	/******************************************************************/
	/*			  		  SECCIÓN PUT (MODIFICAR)					  */
	/******************************************************************/

	/** 
	 * 
	 * Ejemplo: http://localhost:8010/usuarios
	 * 	 * JSON
	 * 
		{
		"email": 200@mail.com",
		"nombre": "Antonio",
		"apellido1": "Alcantara",
		"apellido2": "Palotes",
		"ciudad": "Madrid",
		"telefono": "987654321",
		"fechaAlta": "2054-10-20",
		"contraseña": "123456",
		}
	 * @throws Exception 
	 * */
	@RequestMapping(value="/productos", method = RequestMethod.PUT)
	public Usuario modificar(@RequestBody Usuario usuarioCrear){
		System.out.println("Modificar usuario");

		if(usuarioCrear.getEmail()==null){
			throw new DataIntegrityViolationException("Debe indicar un email de usuario");
		}

		/* Se recupera el usuario original */
		Usuario usuario = usuarioPorEmail(usuarioCrear.getEmail());

		/* Se efectuan las modificaciones que sean necesarias. Los setters ya controlan que no sean null */
		usuario.setEmail(usuarioCrear.getEmail());
		usuario.setNombre(usuarioCrear.getNombre());
		usuario.setApellido1(usuarioCrear.getApellido1());
		usuario.setApellido2(usuarioCrear.getApellido2());
		usuario.setCiudad(usuarioCrear.getCiudad());
		usuario.setTelefono(usuarioCrear.getTelefono());
		usuario.setFechaAlta(usuario.getFechaAlta());
		usuario.setContraseña(usuarioCrear.getContraseña());	
		

		return usuarioRepository.save(usuario);
	}
	
	/******************************************************************/
	/*			 		  SECCIÓN DELETE (BORRAR)					  */
	/******************************************************************/

	/** 
	 * 
	 * Ejemplo: http://localhost:8010/usuarios
	 * 
	 * @throws Exception 
	 * */
	@RequestMapping(value="/usuarios/{email}", method = RequestMethod.DELETE)
	public void eliminar(@PathVariable("email") String email){
		System.out.println("Eliminar producto con id: "+email);

		if(email==null){
			throw new DataIntegrityViolationException("Debe indicar un email de usuario");
		}
		
		/* Se consulta que ese id de producto existe */
		usuarioPorEmail(email);
		
		usuarioRepository.delete(email);
	}

	

}
