package handlers;

import entityManagers.UserManager;

/**UnsubscribeRequestHandler --> Se encarga de dar de baja a un usuario
 * del sistema*/
public class UnsubscribeRequestHandler extends ActionHandler {

	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recupera el email del usuario a modificar
		String emailUsuario =  (String) request.getAttribute("emailUserModificar");

		//Buscamos al usuario en la BBDD
		UserManager userManager = new UserManager();

		//Damos de baja al usuario en la BBDD
		try{
			message = userManager.darDeBaja(emailUsuario);
		}
		catch(Exception e){
			message = message+" "+"Error en la baja del usuario"+".";
			throw new Exception(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
	}

}
