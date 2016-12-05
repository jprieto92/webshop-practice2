package handlers;

import javax.persistence.NoResultException;
import java.util.List;
import entitiesJPA.Categoria;
import entityManagers.CategoriaManager;

public class ShowFormAdvancedRequestHandler extends ActionHandler {
	public void execute () throws Exception {	
	
		//Se pasar�n las categor�as que debe mostrar en el formulario, cargadas de la BBDD
		CategoriaManager gestorCategorias = new CategoriaManager();
		List<Categoria> categoriasBBDD;
		try{
			categoriasBBDD =  gestorCategorias.buscarTodas();
		}
		catch(NoResultException e){
			throw new NoResultException("No existen categor�as");
		}		
		
		request.setAttribute("listaDeCategorias", categoriasBBDD);
	}
}