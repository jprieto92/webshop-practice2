package handlers;

import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.ProductManager;

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
		/*Obtenemos los productos del usuario de la sesion para mostraselos*/
		List<Producto> productos;
		ProductManager gestorDatos = new ProductManager();
		try {
			productos = gestorDatos.buscarPorUsuario(emailUsuarioSession);
		}catch(NoResultException e){
			message = message+" "+e.getMessage()+".";
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}

		request.setAttribute("listaDeProductos", productos);
	}

}