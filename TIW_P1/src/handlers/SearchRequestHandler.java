package handlers;

import java.util.List;
import javax.persistence.NoResultException;
import entitiesJPA.Producto;
import entityManagers.ProductManager;

/**SearchRequestHandler --> Se encarga de realizar la busqueda simple
 * por coincidencia en el titulo*/
public class SearchRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		/*Realizamos la busqueda simple de productos y retornamos las coincidencias obtenidas*/
		String terminoBusqueda = request.getParameter("campoBusqueda");

		List<Producto> productos = null;
		ProductManager gestorDatos = new ProductManager();

		try {
			productos = gestorDatos.busquedaSimpleTituloDescripccion(terminoBusqueda);
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
