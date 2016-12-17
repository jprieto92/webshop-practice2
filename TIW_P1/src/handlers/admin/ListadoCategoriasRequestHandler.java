package handlers.admin;

import java.util.Arrays;
import java.util.List;

import entitiesJPA.Categoria;

import handlers.ActionHandler;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**ListadoUsuariosRequestHandler --> Se encarga de consultar
 * los usuarios de la bbdd y devolver una lista con aquellos
 * que no son admin*/
public class ListadoCategoriasRequestHandler extends ActionHandler {
	 
 	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		List<Categoria> listaCategorias = null;

		try {
			WebTarget webResource = client.target("http://localhost:8050").path("categorias");
			listaCategorias = Arrays.asList(webResource.request().accept("application/json").get(Categoria[].class));

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

		request.setAttribute("listaCategorias", listaCategorias);
 		
 	}
 }
