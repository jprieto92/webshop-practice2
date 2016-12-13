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
public class ModificarCategoriaRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		String idNuevaCategoria = (request.getParameter("idCategoria"));
		String nombre = (request.getParameter("nombreCategoria"));
		String descripcion = (request.getParameter("descripcionCategoria"));
		
		CategoriaManager gestorCategoria = new CategoriaManager();
		Categoria categoriaBBDD;
		try{
			categoriaBBDD =  gestorCategoria.buscarPorId(Integer.parseInt(idNuevaCategoria));
		}
		catch(NoResultException e){
			message = message+" "+"No existe el la categoria"+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
		//Actualizamos el usuario en la BBDD
		categoriaBBDD.setNombre(nombre);
		categoriaBBDD.setDescripccion(descripcion);
		try{
			message = message+" ."+gestorCategoria.modificar(categoriaBBDD);
		}
		catch(Exception e){
			message = message+" "+"Error en la modificación de la categoria"+".";
			throw new Exception(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
	}

}