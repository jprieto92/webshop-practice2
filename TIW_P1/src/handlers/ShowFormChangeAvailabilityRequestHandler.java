package handlers;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;

/**ShowFormChangeAvailabilityRequestHandler --> Se encarga de mostrar 
 * las diferentes opciones para el cambio de la disponibilidad de un producto*/
public class ShowFormChangeAvailabilityRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}

		//Se recoge la idProducto de la peticion
		String idProducto = request.getParameter("idProducto");

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

		//REST Client using GET Verb and Path Variable
		List<Disponibilidad> disponibilidadesBBDD = null;

		try {
			WebTarget webResource = client.target("http://localhost:8070").path("disponibilidades");
			disponibilidadesBBDD = Arrays.asList(webResource.request().accept("application/json").get(Disponibilidad[].class));

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

		request.setAttribute("listaDeDisponibilidades", disponibilidadesBBDD);
		request.setAttribute("idProducto", idProducto);
		request.setAttribute("disponibilidadProducto", productoBBDD.getDisponibilidad());
	}

}