package handlers.admin;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;

import entitiesJPA.Categoria;
import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entityManagers.CategoriaManager;
import entityManagers.DisponibilidadManager;
import entityManagers.ProductManager;
import handlers.ActionHandler;

/**ChangeAvailabilityRequestHandler --> Manejador que cambia la disponibilidad
 * de un producto*/
public class ShowFormInsertarCategoriaRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		request.setAttribute("Message", message);
	}

}