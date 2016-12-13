package handlers.admin;

import javax.persistence.NoResultException;

import entitiesJPA.Categoria;
import entityManagers.CategoriaManager;
import entityManagers.ProductManager;
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
		
		//Se crea la categoria en la BBDD
		CategoriaManager gestorDatos = new CategoriaManager();
		Categoria nuevaCategoria = new Categoria();
		
		nuevaCategoria.setNombre(nombre);
		nuevaCategoria.setDescripccion(descripcion);
		
		try {
			message = message+". "+gestorDatos.insertar(nuevaCategoria);
		}catch(Exception e){
			message = message+" "+e.getMessage()+".";
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}	
	}

}
