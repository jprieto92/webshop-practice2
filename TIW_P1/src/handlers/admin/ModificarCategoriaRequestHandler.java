package handlers.admin;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import entitiesJPA.Categoria;

import handlers.ActionHandler;

/**ChangeAvailabilityRequestHandler --> Manejador que cambia la disponibilidad
 * de un producto*/
public class ModificarCategoriaRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		int idNuevaCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		String nombre = (request.getParameter("nombreCategoria"));
		String descripcion = (request.getParameter("descripcionCategoria"));
		
		/* Se genera la nueva categoria */
		Categoria categoriaAInsertar = new Categoria();
		categoriaAInsertar.setNombre(nombre);
		categoriaAInsertar.setDescripccion(descripcion);
		categoriaAInsertar.setIdCategoria(idNuevaCategoria);

		//REST Client using POST Verb and JSON
		Client client = ClientBuilder.newClient();
		Categoria categoriaInsertada = null;

		try {
			WebTarget webResource = client.target("http://localhost:8050").path("categorias");
			categoriaInsertada = webResource.request("application/json").accept("application/json").put(Entity.entity(categoriaAInsertar,MediaType.APPLICATION_JSON),Categoria.class);

		}catch(NotFoundException e){
			message = "No existe la categoria con el id"+categoriaAInsertar.getIdCategoria()+".";
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