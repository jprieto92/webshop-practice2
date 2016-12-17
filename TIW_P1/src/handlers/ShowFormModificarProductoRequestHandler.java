package handlers;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Categoria;
import entitiesJPA.Producto;

/**ShowFormModificarProductoRequestHandler --> Se encarga de de obtener 
 * los distintos parametros para el formulario de modificacion de producto*/
public class ShowFormModificarProductoRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}

		String idProducto= request.getParameter("idProducto");

		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		Producto productoBBDD = null;

		try {
			WebTarget webResource = client.target("http://localhost:8020").path("productos")
					.path(idProducto);

			productoBBDD = webResource.request().accept("application/json").get(Producto.class);

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



		//Se pasarán las categorías que debe mostrar en el formulario, cargadas de la BBDD
		//REST Client using GET Verb
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
		request.setAttribute("productoModificar", productoBBDD);
	}

}




