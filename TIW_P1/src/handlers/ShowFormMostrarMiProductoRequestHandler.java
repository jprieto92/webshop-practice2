package handlers;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.DisponibilidadManager;


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
		
		//Se obtiene el primer usuario (solo habrá un unico usuario, ya que un producto solo pertenece a un usuario)
		Usuario usuarioBBDD = usuariosBBDD.get(0);
		
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
