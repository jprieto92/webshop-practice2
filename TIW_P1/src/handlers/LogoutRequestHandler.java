package handlers;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

/**LogoutRequestHandler --> Se encarga de gestionar la peticion de logout de la pagina
 * cerrando la sesion del user*/
public class LogoutRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recupera la sesi�n. (Si no existe, no se crea una nueva).
 		HttpSession session = request.getSession(true);
 		
 		//Se invalida la sesi�n

 		
		try{
	 		session.invalidate();
		}catch(IllegalStateException e){
			message = message+" "+"Ha habido un problema al cerrar la sesi�n"+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
 		
	}
}
