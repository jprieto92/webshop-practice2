package handlers;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entitiesJPA.Usuario;


/** ShowFormMostrarMiProductoRequestHandler --> Se encarga de 
 * obtener los distintos parametros para el formulario de mostrar
 * un producto del usuario de la sesion*/
public class ShowFormMostrarMiProductoRequestHandler extends ActionHandler{

	public void execute () throws Exception {
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
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
		//REST Client using GET Verb and Path Variable
		List<Usuario> usuariosBBDD = null;

		try {
			WebTarget webResource = client.target("http://localhost:8010").path("usuarios")
					.queryParam("productId", idProducto);
			usuariosBBDD = Arrays.asList(webResource.request().accept("application/json").get(Usuario[].class));

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

		//Se obtiene el primer usuario (solo habr� un unico usuario, ya que un producto solo pertenece a un usuario)
		Usuario usuarioBBDD = usuariosBBDD.get(0);

		//REST Client using GET Verb and Path Variable
		List<Disponibilidad> disponibilidadesBBDD = null;

		try {
			WebTarget webResource = client.target("http://localhost:8070").path("disponibilidades");
			disponibilidadesBBDD = Arrays.asList(webResource.request().accept("application/json").get(Disponibilidad[].class));

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

		request.setAttribute("listaDeDisponibilidades", disponibilidadesBBDD);
		request.setAttribute("usuarioMostrar", usuarioBBDD);
		request.setAttribute("idProducto", productoBBDD);
	}

}
