package handlers;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.DisponibilidadManager;
import entityManagers.ProductManager;
import entityManagers.UserManager;

/** ShowFormMostrarMiProductoRequestHandler --> Se encarga de 
 * obtener los distintos parametros para el formulario de mostrar
 * un producto del usuario de la sesion*/
public class ShowFormMostrarMiProductoRequestHandler extends ActionHandler{
	
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		String idProducto= request.getParameter("idProducto");
			
		
		
		//REST Client using GET Verb and Path Variable
		Client client = ClientBuilder.newClient();
		Producto productoBBDD = null;
		
		try {
			WebTarget webResource = client.target("http://localhost:8020").path("productos")
					.path(idProducto);
			productoBBDD = webResource.request().accept("application/json").get(Producto.class);

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
		
		/** 
		 * AQUI HAY QUE OBTENER EL USUARIO DEL PRODUCTO DE ALGUNA FORMA. AMTES SE SACABA DEL PROPIO PRODUCTO, AHORA DE LA SESION. POR AHORA PARECE QUE NO HAY PROBLEMAS
		 * */
		//String emailUsuarioSession= productoBBDD.getUsuario().getEmail();
		
		//Se recupera el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		String emailUsuarioSession =  (String) session.getAttribute("userEmailSession");
		
		UserManager gestorUsuario = new UserManager();
		Usuario usuarioBBDD;
		try{
			usuarioBBDD =  gestorUsuario.buscarPorEmail(emailUsuarioSession);
		}
		catch(NoResultException e){
			message = message+" "+"No existe el usuario"+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
		
		
		//Se pasarán las categorías que debe mostrar en el formulario, cargadas de la BBDD
		DisponibilidadManager gestorDisponibilidades = new DisponibilidadManager();
		List<Disponibilidad> disponibilidadesBBDD;
		try{
				disponibilidadesBBDD =  gestorDisponibilidades.buscarTodas();
		}
		catch(NoResultException e){
				message = message+" "+"No existen disponibilidades"+".";
				throw new NoResultException(message);
		}		
		finally{
				request.setAttribute("Message", message);
		}

		request.setAttribute("listaDeDisponibilidades", disponibilidadesBBDD);
		request.setAttribute("usuarioMostrar", usuarioBBDD);
		request.setAttribute("idProducto", productoBBDD);
	}

}
