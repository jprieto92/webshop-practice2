package handlers;

import javax.persistence.NoResultException;
import entitiesJPA.Usuario;
import entityManagers.UserManager;

/** ShowFormModificarUsuarioRequestHandler --> Se encarga de cargar las diferentes
 * opciones para el formulario de modificacion de usuario*/
public class ShowFormModificarUsuarioRequestHandler  extends ActionHandler{
	public void execute () throws Exception {
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recupera el email del usuario a modificar
		String emailUsuario =  (String) request.getAttribute("emailUserModificar");
		
		//Buscamos al usuario en la BBDD
		UserManager userManager = new UserManager();
		Usuario usuarioBBDD = null;
		try{
			usuarioBBDD = userManager.buscarPorEmail(emailUsuario);

		}catch(NoResultException e){
			message = message+" "+e.getMessage()+".";
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}
		
		// Se a�aden a la petici�n el usuario
		request.setAttribute("userEntity", usuarioBBDD);
	}
}