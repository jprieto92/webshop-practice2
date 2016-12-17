package handlers;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.util.Arrays;
import java.util.List;
import entitiesJPA.Categoria;

/**ShowFormCreateProductHandler --> Se encarga de devolver las distintas 
 * categorias de un producto para su creacion en el jsp*/
public class ShowFormCreateProductHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}

		//Se pasarán las categorías que debe mostrar en el formulario, cargadas de la BBDD
		//REST Client using GET Verb
		Client client = ClientBuilder.newClient();
		List<Categoria> categoriasBBDD = null;

		try {
			WebTarget webResource = client.target("http://localhost:8050").path("categorias");
			categoriasBBDD = Arrays.asList(webResource.request().accept("application/json").get(Categoria[].class));

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

		request.setAttribute("listaDeCategorias", categoriasBBDD);
	}
}
