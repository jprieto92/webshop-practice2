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
import entitiesJPA.Producto;

/**InboxRequestHandler --> Se encarga de obtener las conversaciones
 * pendientes para mostrar el indice en la bandeja de entrada*/
public class InboxRequestHandler extends ActionHandler {

	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");		
		if(message == null){
			message = "";
		}
		/*Obtenemos los chats que tenemos pendientes
		 * obteniendo los usuarios que nos han enviado mensajes*/
		HttpSession sesion = request.getSession(false);
		String usuarioSession = (String) sesion.getAttribute("userEmailSession");

		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();

		// Pedimos los emisores que nos han enviado mensajes
		List<Mensaje> nuevosMensajes = null;

		try {
			WebTarget webResource = client.target("http://localhost:8030").path("users").queryParam("email", usuarioSession);
			nuevosMensajes = Arrays.asList(webResource.request().accept("application/json").get(Mensaje[].class));

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
		
		List<String> listaEmisores = new ArrayList<String>();
		/* Se sacan los emisores de la lista de mensajes */
		for(int i = 0; i < nuevosMensajes.size(); i++){
			listaEmisores.add(nuevosMensajes.get(i).getEmisor());
		}

		if(listaEmisores!=null && listaEmisores.size()>0)
		{
			System.out.println("CONVERSACIONES QUE ESTOY RECIBIENDO EN INBOX : ---" + listaEmisores.size());
		}
		/*Aquí debemos de leer de la cola de JMS para enviarle al form los mensajes recibidos*/
		request.setAttribute("conversacionesNuevas", listaEmisores);
	}

}
