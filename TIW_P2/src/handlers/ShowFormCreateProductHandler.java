package handlers;

import javax.persistence.NoResultException;
import java.util.List;
import entitiesJPA.Categoria;
import entityManagers.CategoriaManager;

/**ShowFormCreateProductHandler --> Se encarga de devolver las distintas 
 * categorias de un producto para su creacion en el jsp*/
public class ShowFormCreateProductHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se pasar�n las categor�as que debe mostrar en el formulario, cargadas de la BBDD
		CategoriaManager gestorCategorias = new CategoriaManager();
		List<Categoria> categoriasBBDD;
		try{
			categoriasBBDD =  gestorCategorias.buscarTodas();
		}
		catch(NoResultException e){
			message = message+" "+e.getMessage()+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
		
		request.setAttribute("listaDeCategorias", categoriasBBDD);
	}
}
