package handlers;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

/**LogoutRequestHandler --> Se encarga de gestionar la peticion de logout de la pagina
 * cerrando la sesion del user*/
public class LogoutRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recupera la sesión. (Si no existe, no se crea una nueva).
 		HttpSession session = request.getSession(true);
 		
 		//Se invalida la sesión

 		
		try{
	 		session.invalidate();
		}catch(IllegalStateException e){
			message = message+" "+"Ha habido un problema al cerrar la sesión"+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
 		
	}
}
