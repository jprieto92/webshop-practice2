package es.uc3m.tiw.chat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import es.uc3m.tiw.chat.domains.*;

@RestController
// The following annotation is necessary to allow 
// different domains to be able to use this microservice
// La siguiente anotación es necesaria para permitir 
// que diferente dominios puedan usar este microservicio
@CrossOrigin
public class Controller {

	@Autowired
	MensajeRepository mensajeRepository;
	@Autowired
	UsuarioRepository usuarioRepository;

	/******************************************************************/
	/*			    SECCIÓN GET (BÚSQUEDAS/CONSULTAS)				  */
	/******************************************************************/

	/**
	 * Busqueda de conversaciones abiertas
	 * @return Devuelve una lista de usuarios.
	 *  */
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public List<Usuario> conversaciones(
			@RequestParam(value="email", required=false) String email) 
			{
				return mensajeRepository.findConversations(email);
			}


	/**
	 * Búsqueda de una conversacion
	 * @return Devuelve una conversacion completa.
	 * @param idProducto Establece la búsqueda del producto por dicho parámetro
	 *  */
	@RequestMapping(value="/mensajes", method = RequestMethod.GET)
	public List<Mensaje> conversacion(
			@RequestParam(value="user1", required=false) String user1,
			@RequestParam(value="user1", required=false) String user2)
			{
				
				return mensajeRepository.findOneConversation(user1,user2);
			}


	/******************************************************************/
	/*			  		  SECCIÓN POST (CREAR)						  */
	/******************************************************************/

	/** 
	 * 
	 * Ejemplo: http://localhost:8020/crear
	 * 	 * JSON
	 * {
		"descripccion": "Coche azul modelo",
		"envios": "SI",
		"fechaPublicacion": "2016-10-20",
		"precio": 2500,
		"precioNegociable": "SI",
		"titulo": "COCHES",
		"email": "100@mail.com",
		"idCategoria": 1
		}
	 * */
	@RequestMapping(value="/mensajes", method = RequestMethod.POST)
	public Mensaje nuevoMensaje(@RequestBody MensajeCrear mensajeCrear){

		Mensaje mensaje = new Mensaje(mensajeCrear.getMensaje());
		/* Se establece el emisor del mensaje */
		mensaje.setEmisor(usuarioRepository.findOne(mensajeCrear.getEmisor()));
		/* Se establece el receptor del mensaje */
		mensaje.setEmisor(usuarioRepository.findOne(mensajeCrear.getReceptor()));
		/* Se establece la fecha de creación*/
		mensaje.setFechaPublicacion(new java.util.Date());

		System.out.println("Almacenar mensaje");

		Mensaje mensaje2 = mensajeRepository.save(mensaje);

		System.out.println(mensaje2.getMensajeId());
		return mensaje2;
	}


}