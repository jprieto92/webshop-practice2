package handlers;

import javax.servlet.http.HttpSession;

public class LogoutRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		
		//Se recupera la sesión. (Si no existe, no se crea una nueva).
 		HttpSession session = request.getSession(true);
 		
 		//Se invalida la sesión
 		session.invalidate();
	}
}
