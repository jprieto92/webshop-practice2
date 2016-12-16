package handlers;

import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.ProductManager;
import entityManagers.UserManager;

/**ShowFormMostrarProducto --> Se encarga de generar los parametros necesarios
 * para mostrar un producto a un admin*/
public class ShowFormMostrarProductoAdminRequestHandler extends ActionHandler{
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
		 * HAY QUE LLAMAR AL MICROSERVICIO USUARIO QUE DADO UN ID DE PRODUCTO DEVUELVA UN USUARIO
		 * */
		
		//ESTA LLAMADA YA NO SE PUEDE HACER, YA QUE UN PRODUCTO NO LLEVA EL USUARIO. Temporalmente para pruebas se establece un usuario.
//		String email = productoBBDD.getUsuario().getEmail();
		String email = "100303631@alumnos.uc3m.es";
		
		UserManager gestorUsuario = new UserManager();
		Usuario usuarioBBDD;
		try{
			usuarioBBDD =  gestorUsuario.buscarPorEmail(email);
		}
		catch(NoResultException e){
			message = message+" "+"No existe el usuario"+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
		
		request.setAttribute("usuarioMostrar", usuarioBBDD);
		request.setAttribute("productoMostrar", productoBBDD);
	}
}
