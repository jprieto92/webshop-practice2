package handlers;

import java.util.Arrays;
import java.util.List;
import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Producto;
import entityManagers.ProductManager;

/**SearchRequestHandler --> Se encarga de realizar la busqueda simple
 * por coincidencia en el titulo*/
public class SearchRequestHandler extends ActionHandler {

	public void execute () throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}

		/*Realizamos la busqueda simple de productos y retornamos las coincidencias obtenidas*/
		String terminoBusqueda = request.getParameter("campoBusqueda");

		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		List<Producto> productos = null;

		try {
			WebTarget webResource = client.target("http://localhost:8020").path("productosBusquedaSimple")
					.queryParam("terminoBusqueda", terminoBusqueda);

			productos = Arrays.asList(webResource.request().accept("application/json").get(Producto[].class));

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

		request.setAttribute("listaDeProductos", productos);

	}

}
