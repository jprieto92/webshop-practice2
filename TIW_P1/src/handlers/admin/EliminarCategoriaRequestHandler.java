package handlers.admin;

import java.util.List;

import javax.persistence.NoResultException;

import entitiesJPA.Producto;
import entityManagers.CategoriaManager;
import entityManagers.ProductManager;
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
		int idCategoria = Integer.parseInt((request.getParameter("seleccionarCategoria")));
		
		//Se comprueba que no existen productos con ese idCategoria
		ProductManager gestorProdutos = new ProductManager();
		CategoriaManager gestorCategorias = new CategoriaManager();
		try {
			//Comprueba si existen productos asociados
			message = gestorProdutos.comprobarProductosAsociadosCategoria(idCategoria);
			
			//Si no existen, se procede a borrar la categoria
			message = gestorCategorias.darDeBaja(idCategoria);
		}catch(NoResultException e){
			message = message+" "+e.getMessage()+".";
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}
	}

}
