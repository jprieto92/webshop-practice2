package handlers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Producto;
import entityManagers.ProductManager;

/**CatalogRequestHandler --> Maneja  la obtencion de productos
 * para el catalogo*/
public class CatalogRequestHandler extends ActionHandler {

	public void execute () throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		List<Producto> productos = null;
		
		try {
			WebTarget webResource = client.target("http://localhost:8020").path("productos");
			productos = (List<Producto>) webResource.request().accept("application/json").get(Producto.class);
		
		}catch(NoResultException e){
			message = message+" "+e.getMessage()+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
	
		request.setAttribute("listaDeProductos", productos);
	}

}
