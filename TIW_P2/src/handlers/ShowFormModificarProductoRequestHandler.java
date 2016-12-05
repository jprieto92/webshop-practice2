package handlers;

import java.util.List;

import javax.persistence.NoResultException;
import entitiesJPA.Categoria;
import entitiesJPA.Producto;
import entityManagers.CategoriaManager;
import entityManagers.ProductManager;

/**ShowFormModificarProductoRequestHandler --> Se encarga de de obtener 
 * los distintos parametros para el formulario de modificacion de producto*/
public class ShowFormModificarProductoRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		String idProducto= request.getParameter("idProducto");

		List<Categoria> categoriasBBDD;
		
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
		
		CategoriaManager gestorCategorias = new CategoriaManager();
		try{
			categoriasBBDD =  gestorCategorias.buscarTodas();
		}
		catch(NoResultException e){
			message = message+" "+"No existen categorias"+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}	
		
		request.setAttribute("listaDeCategorias", categoriasBBDD);
		request.setAttribute("productoModificar", productoBBDD);
	}

}


	

