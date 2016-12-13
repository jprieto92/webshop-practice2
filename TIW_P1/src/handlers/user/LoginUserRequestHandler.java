package handlers.user;
 
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import entitiesJPA.Usuario;
import entityManagers.UserManager;
import handlers.ActionHandler;
 
 public class LoginUserRequestHandler extends ActionHandler {
 
 	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
 		
		//Recuperacion campos formulario login
 		String email = (String) request.getParameter("emailLogin");
 		String pass = (String) request.getParameter("passHashLogin");
 		
		//Comprobar que el usuario existe en la BBDD. 
		UserManager gestorDatosUsuario = new UserManager();

		String emailUserBBDD;
		try{
			//Se le pasa el 1 como id de tipo de usuario, que corresponde a user
			emailUserBBDD = gestorDatosUsuario.comprobarCredencialesDevuelveEmail(email, pass, 1);
		}
		catch(NoResultException e){
			message = message + " " +e.getMessage()+".";
			throw new NoResultException(e.getMessage());
 		}
		finally{
			request.setAttribute("Message", message);
		}
 		//Si existe el usuario, se procede a crear la sesion
 		HttpSession session = request.getSession(true);

		//Añadimos a la sesion el email del usuario obtenido de la BBDD
		session.setAttribute("userEmailSession", emailUserBBDD);
 	}
 	
 }
