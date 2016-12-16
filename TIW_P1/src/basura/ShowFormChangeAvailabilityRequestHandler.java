package basura;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entityManagers.DisponibilidadManager;
import entityManagers.ProductManager;
import handlers.ActionHandler;

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
		int idProducto = Integer.parseInt(request.getParameter("idProducto"));

		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		Producto producto = null;

		try {
			WebTarget webResource = client.target("http://localhost:8020").path("idProducto");
			producto = webResource.request().accept("application/json").get(Producto.class);

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
		
		Disponibilidad disponibilidadProducto = producto.getDisponibilidad();
		
		//Se pasarán las categorías que debe mostrar en el formulario, cargadas de la BBDD
		DisponibilidadManager gestorDisponibilidades = new DisponibilidadManager();
		List<Disponibilidad> disponibilidadesBBDD;
		try{
			disponibilidadesBBDD =  gestorDisponibilidades.buscarTodas();
		}
		catch(NoResultException e){
			message = message+" "+"No existen disponibilidades"+".";
			throw new NoResultException(message);
		}		
		finally{
			request.setAttribute("Message", message);
		}
		
		request.setAttribute("listaDeDisponibilidades", disponibilidadesBBDD);
		request.setAttribute("idProducto", idProducto);
		request.setAttribute("disponibilidadProducto", disponibilidadProducto);
	}

}