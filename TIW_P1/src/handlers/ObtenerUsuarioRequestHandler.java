package handlers;


import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Usuario;
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

		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		Usuario usuarioBBDD = null;

		try {
			WebTarget webResource = client.target("http://localhost:8010").path("usuarios")
					.path(emailUsuarioSession);
			usuarioBBDD = webResource.request().accept("application/json").get(Usuario.class);

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

		Integer tipoUsuarioId = usuarioBBDD.getTipoUsuario().getIdTipoUsuario();

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