package handlers;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Usuario;

/**UnsubscribeRequestHandler --> Se encarga de dar de baja a un usuario
 * del sistema*/
public class UnsubscribeRequestHandler extends ActionHandler {

	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recupera el email del usuario a modificar
		String emailUsuario =  (String) request.getAttribute("emailUserModificar");

		///REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		
		try {
			WebTarget webResource = client.target("http://localhost:8010").path("usuarios")
					.path(emailUsuario);
			webResource.request().accept("application/json").delete();

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

	}

}
