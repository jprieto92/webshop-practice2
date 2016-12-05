package handlers;

import javax.persistence.NoResultException;
import java.util.List;
import entitiesJPA.Categoria;
import entityManagers.CategoriaManager;

public class ShowFormCreateProductHandler extends ActionHandler {
	public void execute () throws Exception {
		request.setAttribute("createProductMessage", "Si est�s leyendo esto, es porque la petici�n ha sido leida por el manejador de ShowFormCreateProductHandler.");
	
	
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
