package handlers;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entitiesJPA.ProductoCrear;

/**ChangeAvailabilityRequestHandler --> Manejador que cambia la disponibilidad
 * de un producto*/
public class ChangeAvailabilityRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recuperan los campos de la petición
		Integer idNuevaDisponibilidad = Integer.parseInt(request.getParameter("disponibilidadProducto"));
		Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
		
		//Se crea un producto con solo las modificaciones que sufrirá el producto
		ProductoCrear modificacionesProducto = new ProductoCrear();
		modificacionesProducto.setProductId(idProducto);
		modificacionesProducto.setIdDisponibilidad(idNuevaDisponibilidad);
		
		
		//REST Client using PUT Verb and JSON
		Client client = ClientBuilder.newClient();
		Producto productoInsertado = null;

		try {
			WebTarget webResource = client.target("http://localhost:8020").path("productos");
			productoInsertado =	webResource.request("application/json").accept("application/json").put(Entity.entity(modificacionesProducto,MediaType.APPLICATION_JSON),Producto.class);

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
		
		
	}

}