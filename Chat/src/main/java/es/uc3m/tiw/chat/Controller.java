package es.uc3m.tiw.chat;

import java.util.ArrayList;
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
	public List<Mensaje> conversaciones(@RequestParam(value="email", required=false) String email) 
	{
		System.out.println("conversaciones");
		List<Mensaje> listaMensajes = mensajeRepository.findConversations(email);

		/* Se limpian los emisores duplicados */
		List<Mensaje> listaMensajesAux = new ArrayList<Mensaje>();
	
		
		if(!listaMensajes.isEmpty()) listaMensajesAux.add(listaMensajes.get(0));

		// Obtenemos los emisores cuando email receptor
		for(int i = 1; i < listaMensajes.size(); i++)
		{
			int libre = 0;
			int j=0;
			for(j = 0; j < listaMensajesAux.size(); j++){
				if(listaMensajes.get(i).getEmisor().equals(email) || 
					listaMensajes.get(i).getEmisor().equals(listaMensajesAux.get(j).getEmisor())){
					libre = 1;
					break;
				}
			}
			if(libre == 0)
			{
				listaMensajesAux.add(listaMensajes.get(i));
			}
		}
		// Obtenemos los receptores cuando email emisor
		for(int i = 0; i < listaMensajes.size(); i++)
		{
			int libre = 0;
			int j=0;
			for(j = 0; j < listaMensajesAux.size(); j++){
				if(!(listaMensajes.get(i).getEmisor().equals(email)) || 
					listaMensajes.get(i).getReceptor().equals(listaMensajesAux.get(j).getEmisor()) ||
					listaMensajes.get(i).getReceptor().equals(listaMensajesAux.get(j).getReceptor())){
					libre = 1;
					break;
				}
			}
			if(libre == 0)
			{
				listaMensajesAux.add(listaMensajes.get(i));
			}
		}
		System.out.println("Conversaciones abiertas obtenidas");

		return listaMensajesAux;
	}


	/**
	 * Búsqueda de una conversacion
	 * @return Devuelve una conversacion completa.
	 * @param idProducto Establece la búsqueda del producto por dicho parámetro
	 *  */
	@RequestMapping(value="/mensajes", method = RequestMethod.GET)
	public List<Mensaje> conversacion(
			@RequestParam(value="user1", required=false) String user1,
			@RequestParam(value="user2", required=false) String user2)
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

		Mensaje mensaje = new Mensaje(mensajeCrear.getEmisor(),mensajeCrear.getReceptor(),mensajeCrear.getMensaje());
		/* Se establece el emisor del mensaje */
		//mensaje.setEmisor(mensajeCrear.getEmisor());
		/* Se establece el receptor del mensaje */
		//mensaje.setEmisor(mensajeCrear.getReceptor());
		/* Se establece la fecha de creación*/
		mensaje.setFechaPublicacion(new java.util.Date());

		System.out.println("Almacenar mensaje");

		Mensaje mensaje2 = mensajeRepository.save(mensaje);

		System.out.println(mensaje2.getMensajeId());
		return mensaje2;
	}


}