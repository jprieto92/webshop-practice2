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

import es.uc3m.tiw.usuario.domains.TipoUsuarioRepository;
import es.uc3m.tiw.usuario.domains.Usuario;
import es.uc3m.tiw.usuario.domains.UsuarioLogin;
import es.uc3m.tiw.usuario.domains.UsuarioRepository;



@RestController

//La siguiente anotación es necesaria para permitir 
//que diferente dominios puedan usar este microservicio
@CrossOrigin
public class Controller {

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;
	@Autowired
	UsuarioRepository usuarioRepository;

	/******************************************************************/
	/*			    SECCIÓN GET (BÚSQUEDAS/CONSULTAS)				  */
	/******************************************************************/

	/**
	 * Búsqueda de todos los usuarios
	 * @return Devuelve una lista de usuarios.
	 *  */
	@RequestMapping(value="/usuarios", method = RequestMethod.GET)
	public List<Usuario> usuarios(@RequestParam(value="productId", required=false) Integer productId,
			@RequestParam(value="terminoFiltrado", required=false) String terminoFiltrado,
			@RequestParam(value="idTipoUsuario", required=false) Integer idTipoUsuario){
		
		/* Si hemos recibido el parámetro, se realizará una búsqueda filtrada por el nombre de usuario*/
		if(terminoFiltrado != null){
			System.out.println("Busca usuarios dado el nombre");
			List<Usuario> usuarios = usuarioRepository.findByNombreContains(terminoFiltrado);
			if(usuarios==null)throw new DataIntegrityViolationException("No existen usuarios con el nombre: "+terminoFiltrado);

			return usuarios;
		}
		/* Si hemos recibido el parámetro, se realizará una búsqueda filtrada por el id de Producto */
		else if(productId != null){
			System.out.println("Busca usuario dado id de Producto");
			List<Usuario> usuarios = usuarioRepository.findByProductoProductId(productId);
			if(usuarios==null)throw new DataIntegrityViolationException("No existe un usuario que tenga asociado el id de producto: "+productId);

			return usuarios;
		}
		else if(idTipoUsuario != null){
			System.out.println("Busca usuario dado id de tipo de usuario");
			List<Usuario> usuarios = usuarioRepository.findByTipoUsuarioIdTipoUsuario(idTipoUsuario);
			if(usuarios==null)throw new DataIntegrityViolationException("No existen usuarios con el id tipo usuario: "+idTipoUsuario);

			return usuarios;
		}
		

		/* En caso contrario, se obtendrán todos los usuarios */
		System.out.println("Buscar todos los Usuarios");
		return usuarioRepository.findAll();
	}

	/**
	 * Búsqueda de un usuario
	 * @return Devuelve un usuario.
	 * @param email Establece la búsqueda del usuario por dicho parámetro
	 *  */
	@RequestMapping(value="/usuarios/{email:.+}", method = RequestMethod.GET)
	public Usuario usuarioPorEmail(@PathVariable("email") String email){
		System.out.println("Busca usuario por email"+email);
		Usuario usuario = usuarioRepository.findOne(email);

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
		"email": "1550@mail.com",
		"nombre": "Perico",
		"apellido1": "Palotes",
		"apellido2": "Fresa",
		"ciudad": "Madrid",
		"telefono": "987654321",
		"fechaAlta": "2054-10-20",
		"contraseña": "123456",
		"imagenPerfil": "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"

		}
	 * */
	@RequestMapping(value="/usuarios", method = RequestMethod.POST)
	public Usuario crear(@RequestBody Usuario usuario){
		System.out.println("Almacenar usuario");
//		System.out.println("El telefono en el microservicio es: "+usuario.getTelefono());
		
		/* Se establece el tipo de usuario por defecto de tipo 'USER' */
		usuario.setTipoUsuario(tipoUsuarioRepository.findOne(1));

		return usuarioRepository.save(usuario);
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public Usuario login(@RequestBody UsuarioLogin usuarioLogin){
		System.out.println("Login usuario");
		return usuarioRepository.findByEmailAndContraseñaAndTipoUsuarioIdTipoUsuario(usuarioLogin.getEmail(), usuarioLogin.getPass(), usuarioLogin.getIdTipoUsuario());
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
		"email": "100@mail.com",
		"nombre": "Antonio",
		"apellido1": "Alcantara",
		"apellido2": "Palotes",
		"ciudad": "Madrid",
		"telefono": "987654321",
		"fechaAlta": "2054-10-20",
		"contraseña": "123456"
		}
	 * @throws Exception 
	 * */
	@RequestMapping(value="/usuarios", method = RequestMethod.PUT)
	public Usuario modificar(@RequestBody Usuario modificacionesUsuario){
		System.out.println("Modificar usuario");

		if(modificacionesUsuario.getEmail()==null){
			throw new DataIntegrityViolationException("Debe indicar un email de usuario para la modificación");
		}

		/* Se recupera el usuario original */
		Usuario usuarioModificado = usuarioPorEmail(modificacionesUsuario.getEmail());

		/* Se efectuan las modificaciones que sean necesarias. Los setters ya controlan que no sean null */
		usuarioModificado.setEmail(modificacionesUsuario.getEmail());
		usuarioModificado.setNombre(modificacionesUsuario.getNombre());
		usuarioModificado.setApellido1(modificacionesUsuario.getApellido1());
		usuarioModificado.setApellido2(modificacionesUsuario.getApellido2());
		usuarioModificado.setCiudad(modificacionesUsuario.getCiudad());
		usuarioModificado.setTelefono(modificacionesUsuario.getTelefono());
		usuarioModificado.setFechaAlta(usuarioModificado.getFechaAlta());
		usuarioModificado.setContraseña(modificacionesUsuario.getContraseña());	
		usuarioModificado.setImagenPerfil(modificacionesUsuario.getImagenPerfil());


		return usuarioRepository.save(usuarioModificado);
	}

	/******************************************************************/
	/*			 		  SECCIÓN DELETE (BORRAR)					  */
	/******************************************************************/

	/** 
	 * 
	 * Ejemplo: http://localhost:8010/usuarios/2
	 * 
	 * @throws Exception 
	 * */
	@RequestMapping(value="/usuarios/{email:.+}", method = RequestMethod.DELETE)
	public void eliminar(@PathVariable("email") String email){
		System.out.println("Eliminar producto con id: "+email);

		if(email==null){
			throw new DataIntegrityViolationException("Debe indicar un email de usuario");
		}

		/* Se consulta que ese id de producto existe. Si no, el propio método lanzará una excepción */
		usuarioPorEmail(email);

		usuarioRepository.delete(email);
	}



}
