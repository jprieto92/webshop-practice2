package handlers;

import javax.persistence.NoResultException;
import java.util.List;
import entitiesJPA.Categoria;
import entityManagers.CategoriaManager;

/**ShowFormAdvancedRequestHandler --> Se encarga de de obtener las distintas
 * categorias para poder realizar la busqueda avanzada*/
public class ShowFormAdvancedRequestHandler extends ActionHandler {
	public void execute () throws Exception {	
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se pasarán las categorías que debe mostrar en el formulario, cargadas de la BBDD
		CategoriaManager gestorCategorias = new CategoriaManager();
		List<Categoria> categoriasBBDD;
		try{
			categoriasBBDD =  gestorCategorias.buscarTodas();
		}catch(NoResultException e){
			message = message+" "+"No existen categorias"+".";
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}	
		
		request.setAttribute("listaDeCategorias", categoriasBBDD);
	}
}