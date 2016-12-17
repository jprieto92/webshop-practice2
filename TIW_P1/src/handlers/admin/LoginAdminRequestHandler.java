package handlers.admin;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import entitiesJPA.Usuario;
import entitiesJPA.UsuarioLogin;
import entityManagers.UserManager;
import handlers.ActionHandler;

public class LoginAdminRequestHandler extends ActionHandler {

	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}

		//Recuperacion campos formulario login
		String email = (String) request.getParameter("emailLogin");
		String pass = (String) request.getParameter("passHashLogin");
		UsuarioLogin usuarioLogin = new UsuarioLogin(email, pass, 2);

		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		Usuario usuario = null;
		HttpSession session = null;

		try {
			WebTarget webResource = client.target("http://localhost:8010").path("login");
			usuario =	webResource.request("application/json").accept("application/json").post(Entity.entity(usuarioLogin,MediaType.APPLICATION_JSON),Usuario.class);
			//Si existe el usuario, se procede a crear la sesion
			session = request.getSession(true);

		}catch(WebApplicationException e){
			message = message+" "+e.getMessage()+".";
			throw e;		
		}
		catch(Exception e){
			throw e;
		}
		finally{
			request.setAttribute("Message", message);
		}

		//Añadimos a la sesion el email del usuario obtenido de la BBDD
		session.setAttribute("userEmailSession", usuario.getEmail());

	}

}
