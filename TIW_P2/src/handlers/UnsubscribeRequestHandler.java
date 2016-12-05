package handlers;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import entitiesJPA.Usuario;
import entityManagers.UserManager;

public class UnsubscribeRequestHandler extends ActionHandler {

	public void execute () throws Exception {

		//Recuperamos el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		Usuario usuarioSession = (Usuario) session.getAttribute("entityUser");

		//Buscamos al usuario en la BBDD

		String message = "";
		UserManager userManager = new UserManager();
		/** Puede valer con cogerlo de la sesion, ya que debe estar actualizado
		//Buscamos al usuario en la BBDD
		Usuario usuarioBBDD = null;
		try{
			usuarioBBDD = userManager.buscarPorEmail(usuarioSession.getEmail());
		}
		catch(NoResultException e){
			message = "Error en la baja del usuario";
			throw new NoResultException("No se ha encontrado el usuario en la BBDD con ese email");
 		}
		finally{
			request.setAttribute("Message", message);
 		}**/

		//Damos de baja al usuario en la BBDD
		try{
			message = userManager.darDeBaja(usuarioSession.getEmail());
		}
		catch(Exception e){
			message = "Error en la baja del usuario";
			throw new Exception("No se ha podido dar de baja al usuario en la BBDD");
		}
		finally{
			request.setAttribute("Message", message);
		}
	}

}
