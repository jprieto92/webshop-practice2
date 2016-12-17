package handlers;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Producto;

/**SearchAdvancedRequestHandler --> Se encarga de realizar la busqueda avanzada*/
public class SearchAdvancedRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recuperan los cinco parámetros de búsqueda
		String campoBusquedaTitulo = request.getParameter("campoBusquedaTitulo");
		String campoBusquedaDescripccion = request.getParameter("campoBusquedaDescripccion");
		String campoBusquedaEmailVendedor = request.getParameter("campoBusquedaEmailVendedor");
		String campoBusquedaCiudad = request.getParameter("campoBusquedaCiudad");
		String campoBusquedaCategoria = request.getParameter("campoBusquedaCategoria");

		
		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		List<Producto> productos = null;
		
		try {
			WebTarget webResource = client.target("http://localhost:8020").path("productos")
					.queryParam("nombreCategoria", campoBusquedaCategoria)
					.queryParam("ciudad", campoBusquedaCiudad)
					.queryParam("email", campoBusquedaEmailVendedor)
					.queryParam("titulo", campoBusquedaTitulo)
					.queryParam("descripccion", campoBusquedaDescripccion);
			
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