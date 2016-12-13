package handlers;

import java.util.List;

import javax.persistence.NoResultException;
import entitiesJPA.Usuario;
import entityManagers.UserManager;

/**ListadoUsuariosRequestHandler --> Se encarga de consultar
 * los usuarios de la bbdd y devolver una lista con aquellos
 * que no son admin*/
public class ListadoUsuariosRequestHandler extends ActionHandler {
	 
 	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		List<Usuario> usuarios = null;
		UserManager gestorDatosUsuario = new UserManager();
		
		//Se buscan todos los usuarios que no sean administradores
		try {
			usuarios = gestorDatosUsuario.buscarTodosUsers();
		}catch(NoResultException e){
			message = message+" "+e.getMessage()+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}

		request.setAttribute("listaDeUsuarios", usuarios);
 		
 	}
 }
