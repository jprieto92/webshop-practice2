package handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Mensaje;

/**ChatRequestHandler --> Manejador que obtiene y consume de la cola JMS
 * todos los mensajes asociados a una conversacion*/
public class ChatRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");		
		if(message == null){
			message = "";
		}
		
		/*Obtenemos todos los mensajes asociados a una conversacion*/
		HttpSession sesion = request.getSession(false);
		String usuarioSession = (String) sesion.getAttribute("userEmailSession");
		String conversacion = (String) request.getParameter("conversacion");
		System.out.println("ID : " + conversacion);
		// Pedimos al microservicio la conversacion
		// Nos retornará los mensajes
		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
				
		// Pedimos los emisores que nos han enviado mensajes
		List<Mensaje> listaMensajes = new ArrayList<Mensaje>();
		
		try {
				WebTarget webResource = client.target("http://localhost:8030").path("mensajes").queryParam("user1", usuarioSession).queryParam("user2", conversacion);
				listaMensajes = Arrays.asList(webResource.request().accept("application/json").get(Mensaje[].class));
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
		
		
		
		System.out.println("MENSAJES QUE ESTOY RECIBIENDO : ---" + listaMensajes.size());
		
		
		request.setAttribute("mensajesRecibidos", listaMensajes);
		request.setAttribute("conversacion", conversacion);
	}

}
