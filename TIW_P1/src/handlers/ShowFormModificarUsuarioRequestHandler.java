package handlers;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Producto;
import entitiesJPA.Usuario;

/** ShowFormModificarUsuarioRequestHandler --> Se encarga de cargar las diferentes
 * opciones para el formulario de modificacion de usuario*/
public class ShowFormModificarUsuarioRequestHandler  extends ActionHandler{
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}

		//Se recupera el email del usuario a modificar
		String emailUsuario =  (String) request.getAttribute("emailUserModificar");

		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		Usuario usuarioBBDD = null;

		try {
			WebTarget webResource = client.target("http://localhost:8010").path("usuarios")
					.path(emailUsuario);
			usuarioBBDD = webResource.request().accept("application/json").get(Usuario.class);

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

		// Se añaden a la petición el usuario
		request.setAttribute("userEntity", usuarioBBDD);
	}
}