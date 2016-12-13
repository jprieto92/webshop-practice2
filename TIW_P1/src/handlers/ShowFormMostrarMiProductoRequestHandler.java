package handlers;

import java.util.List;

import javax.persistence.NoResultException;

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
			
		ProductManager gestorProducto = new ProductManager();
		Producto productoBBDD;
		try{
			productoBBDD =  gestorProducto.buscarPorId(Integer.parseInt(idProducto));
		}
		catch(NoResultException e){
			message = message+" "+"No existe el producto"+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
		String email= productoBBDD.getUsuario().getEmail();
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
