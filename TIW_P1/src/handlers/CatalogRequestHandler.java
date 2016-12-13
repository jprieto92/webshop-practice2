package handlers;

import java.util.List;

import javax.persistence.NoResultException;
import entitiesJPA.Producto;
import entityManagers.ProductManager;

/**CatalogRequestHandler --> Maneja  la obtencion de productos
 * para el catalogo*/
public class CatalogRequestHandler extends ActionHandler {

	public void execute () throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		/*Devolvemos al jsp de catalogo todos los productos*/
		List<Producto> productos = null;
		ProductManager gestorDatos = new ProductManager();

		try {
			productos = gestorDatos.buscarTodos();
		}catch(NoResultException e){
			message = message+" "+e.getMessage()+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}

		request.setAttribute("listaDeProductos", productos);
	}

}
