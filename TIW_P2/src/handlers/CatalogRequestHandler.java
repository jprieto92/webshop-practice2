package handlers;

import java.util.List;

import javax.persistence.NoResultException;
import entitiesJPA.Producto;
import entityManagers.ProductManager;

public class CatalogRequestHandler extends ActionHandler {

	public void execute () throws Exception {		

		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = "";

		List<Producto> productos = null;
		ProductManager gestorDatos = new ProductManager();

		try {
			productos = gestorDatos.buscarTodos();
		}catch(NoResultException e){
			message = e.getMessage();
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}

		request.setAttribute("listaDeProductos", productos);
	}

}
