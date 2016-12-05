package handlers;

import java.util.List;
import javax.persistence.NoResultException;
import entitiesJPA.Producto;
import entityManagers.ProductManager;

/**SearchAdvancedRequestHandler --> Se encarga de realizar la busqueda avanzada*/
public class SearchAdvancedRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recuperan los cinco parámetros de búsqueda
		String campoBusquedaTitulo = request.getParameter("campoBusquedaTitulo");
		String campoBusquedaDescripccion = request.getParameter("campoBusquedaDescripccion");
		String campoBusquedaEmailVendedor = request.getParameter("campoBusquedaEmailVendedor");
		String campoBusquedaCiudad = request.getParameter("campoBusquedaCiudad");
		String campoBusquedaCategoria = request.getParameter("campoBusquedaCategoria");

		
		List<Producto> productos = null;
		ProductManager gestorDatos = new ProductManager();

		try {
			productos = gestorDatos.busquedaAvanzada(campoBusquedaTitulo, campoBusquedaDescripccion, campoBusquedaEmailVendedor, campoBusquedaCiudad, campoBusquedaCategoria);
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