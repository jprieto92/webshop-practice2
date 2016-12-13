package handlers.admin;

import java.util.List;

import javax.persistence.NoResultException;

import entitiesJPA.Categoria;
import entitiesJPA.Usuario;
import entityManagers.CategoriaManager;
import entityManagers.UserManager;
import handlers.ActionHandler;

/**ListadoUsuariosRequestHandler --> Se encarga de consultar
 * los usuarios de la bbdd y devolver una lista con aquellos
 * que no son admin*/
public class ListadoCategoriasRequestHandler extends ActionHandler {
	 
 	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		List<Categoria> listaCategorias = null;
		CategoriaManager gestorDatosCategoria = new CategoriaManager();
		
		//Se buscan todos los usuarios que no sean administradores
		try {
			listaCategorias = gestorDatosCategoria.buscarTodas();
		}catch(NoResultException e){
			message = message+" "+e.getMessage()+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}

		request.setAttribute("listaCategorias", listaCategorias);
 		
 	}
 }
