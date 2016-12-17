package handlers.admin;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import handlers.ActionHandler;

/**ProductRemoveRequestHandler --> Se encarga de eliminar un producto de la 
 * base de datos*/
public class EliminarCategoriaRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}

		//Se recupera el id de la categoria
		String idCategoria = request.getParameter("seleccionarCategoria");

		///REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();

		try {
			WebTarget webResource = client.target("http://localhost:8050").path("categorias")
					.path(idCategoria);
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
