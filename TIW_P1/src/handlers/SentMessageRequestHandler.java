package handlers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import entitiesJPA.Mensaje;
import entitiesJPA.Producto;
import handlers.ActionHandler;

/**SentMessageRequestHandler --> Se encarga de mandar a la cola JMS
 * un nuevo mensaje*/
public class SentMessageRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		/*Escribimos en la cola*/
		String destinatario= request.getParameter("destinatario");
		String emisor= request.getParameter("emisor");
		String mensaje= request.getParameter("mensaje");
		
		Mensaje mensajeInsertado=null;
		
		Mensaje mensajeNuevo = new Mensaje(emisor, destinatario, mensaje);
		//REST Client using POST Verb and JSON
		Client client = ClientBuilder.newClient();
		try {
			WebTarget webResource = client.target("http://localhost:8030").path("mensajes");
			mensajeInsertado =	webResource.request("application/json").accept("application/json").post(Entity.entity(mensajeNuevo,MediaType.APPLICATION_JSON),Mensaje.class);
		}catch(WebApplicationException e){
			message = message+" "+e.getMessage()+".";
			throw e;		
		}
			catch(Exception e){
			throw e;
		}
		finally{
			request.setAttribute("Message", message);
		}
		System.out.println("El mensaje a enviado es: " +  mensaje);

	}

}
