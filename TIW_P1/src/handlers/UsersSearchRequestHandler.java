package handlers;

import java.util.Arrays;
import java.util.List;
import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Usuario;

/**UsersSearchRequestHandler --> Se encarga de la busqueda de un usuario*/
public class UsersSearchRequestHandler extends ActionHandler {

	public void execute () throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}

		String terminoFiltrado = request.getParameter("campoBusqueda");

		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		List<Usuario> usuarios = null;

		try {
			WebTarget webResource = client.target("http://localhost:8010").path("usuarios")
					.queryParam("terminoFiltrado", terminoFiltrado);
			usuarios = Arrays.asList(webResource.request().accept("application/json").get(Usuario[].class));

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

		request.setAttribute("listaDeUsuarios", usuarios);

	}

}