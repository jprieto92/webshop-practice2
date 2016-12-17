package handlers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Producto;


/**MyProductsRequestHandler --> Se encarga de consultar en la base
 * de datos los productos del usuario de la sesion y los devuelve
 * para ser mostrados*/
public class MyProductsRequestHandler extends ActionHandler {

	public void execute () throws Exception {		
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
		List<Producto> productos = null;

		try {
			WebTarget webResource = client.target("http://localhost:8020").path("productos")
					.queryParam("email", emailUsuarioSession);
			productos = Arrays.asList(webResource.request().accept("application/json").get(Producto[].class));

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

		request.setAttribute("listaDeProductos", productos);
	}

}