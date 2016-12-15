package handlers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


/**ProductRemoveRequestHandler --> Se encarga de eliminar un producto de la 
 * base de datos*/
public class ProductRemoveRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recupera el id del producto
		String idProducto = request.getParameter("idProducto");
		
		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();

		try {
			WebTarget webResource = client.target("http://localhost:8020").path("productos")
					.path(idProducto);
			webResource.request().accept("application/json").delete();

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
