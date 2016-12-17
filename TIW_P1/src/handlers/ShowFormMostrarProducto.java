package handlers;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Producto;
import entitiesJPA.Usuario;

/**ShowFormMostrarProducto --> Se encarga de obtener los parametros necesarios
 * para mostrar un producto*/
public class ShowFormMostrarProducto extends ActionHandler{
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
		
		/** 
		 * HAY QUE LLAMAR AL MICROSERVICIO USUARIO QUE DADO UN ID DE PRODUCTO DEVUELVA UN USUARIO
		 * */
		
		//REST Client using GET Verb and Path Variable
		List<Usuario> usuariosBBDD = null;
		
		try {
			WebTarget webResource = client.target("http://localhost:8010").path("usuarios")
					.queryParam("productId", idProducto);
			usuariosBBDD = Arrays.asList(webResource.request().accept("application/json").get(Usuario[].class));

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
		
		//Se obtiene el primer usuario (solo habrá un unico usuario, ya que un producto solo pertenece a un usuario)
		Usuario usuarioBBDD = usuariosBBDD.get(0);
		
		request.setAttribute("usuarioMostrar", usuarioBBDD);
		request.setAttribute("productoMostrar", productoBBDD);
	}
}
