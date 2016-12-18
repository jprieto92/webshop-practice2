package handlers.admin;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Categoria;

import handlers.ActionHandler;

/**ChangeAvailabilityRequestHandler --> Manejador que cambia la disponibilidad
 * de un producto*/
public class ShowFormModificarCategoriaRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}

		String idNuevaCategoria = (request.getParameter("seleccionarCategoria"));
		
		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		Categoria categoriaBBDD = null;

		try {
			WebTarget webResource = client.target("http://localhost:8050").path("categorias")
					.path(idNuevaCategoria);
			categoriaBBDD = webResource.request().accept("application/json").get(Categoria.class);

		}catch(WebApplicationException e){
			message = message+" "+e.getMessage()+".";
			throw e;
		}
		catch(Exception e){
			message = message+" "+e.getMessage()+".";
			throw e;
		}
		finally{
			request.setAttribute("Message", message);
		}

		request.setAttribute("categoriaModificar", categoriaBBDD);


	}

}