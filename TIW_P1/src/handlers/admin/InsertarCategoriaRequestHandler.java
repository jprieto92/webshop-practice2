package handlers.admin;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import entitiesJPA.Categoria;
import entitiesJPA.Producto;
import entityManagers.CategoriaManager;
import handlers.ActionHandler;

/**ProductRemoveRequestHandler --> Se encarga de eliminar un producto de la 
 * base de datos*/
public class InsertarCategoriaRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}

		String nombre = (request.getParameter("nombreCategoria"));
		String descripcion = (request.getParameter("descripcionCategoria"));


		Categoria categoriaAInsertar = new Categoria();
		categoriaAInsertar.setNombre(nombre);
		categoriaAInsertar.setDescripccion(descripcion);

		//REST Client using POST Verb and JSON
		Client client = ClientBuilder.newClient();
		Categoria categoriaInsertada = null;

		try {
			WebTarget webResource = client.target("http://localhost:8050").path("categorias");
			categoriaInsertada = webResource.request("application/json").accept("application/json").post(Entity.entity(categoriaAInsertar,MediaType.APPLICATION_JSON),Categoria.class);

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
