package handlers;


import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import entityManagers.UserManager;
import handlers.ActionHandler;

/**ObtenerUsuarioRequestHandler --> Se encarga de obtener un usuario
 * para posteriormente poderlo modificar etc*/
public class ObtenerUsuarioRequestHandler extends ActionHandler {

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recupera el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		String emailUsuarioSession =  (String) session.getAttribute("userEmailSession");
		
		
		//Buscamos el tipo de usuario en la BBDD
		UserManager userManager = new UserManager();
		Integer tipoUsuarioId = null;
		try{
			tipoUsuarioId = userManager.obtenerIdTipoUsuario(emailUsuarioSession);

		}catch(NoResultException e){
			message = message+" ."+e.getMessage();
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
		
		String emailUserModificar;
		
		//Si el tipo de usuario es admin, estableceremos el email de usuario a modificar a partir del idProducto
		if(tipoUsuarioId==2){
			emailUserModificar = (String) request.getParameter("idUsuario");
			System.out.println("El usuario que se ha seleccionado desde el handler obtenerUsuario tiene el email: "+emailUserModificar);
		}
		//Si se trata de cualquier otro user, se obtendrá de la session
		else{
			emailUserModificar = emailUsuarioSession;
		}
		
		//Se añade a la petición el email del usuario a tratar
		request.setAttribute("emailUserModificar", emailUserModificar);
	}

}