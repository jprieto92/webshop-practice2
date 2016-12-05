package handlers;

import java.util.List;

import javax.persistence.NoResultException;

import com.sun.xml.ws.runtime.dev.Session;

import entitiesJPA.Categoria;
import entitiesJPA.Producto;
import entityManagers.CategoriaManager;
import entityManagers.ProductManager;

public class ShowFormModificarProductoRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		request.setAttribute("createProductMessage", "Si estás leyendo esto, es porque vamos a modificar un producto.");
	
		String idProducto= request.getParameter("idProducto");
		CategoriaManager gestorCategorias = new CategoriaManager();
		List<Categoria> categoriasBBDD;
		
		ProductManager gestorProducto = new ProductManager();
		Producto productoBBDD;
		try{
			productoBBDD =  gestorProducto.buscarPorId(Integer.parseInt(idProducto));
		}
		catch(NoResultException e){
			throw new NoResultException("No existe el producto que se busca");
		}
		try{
			categoriasBBDD =  gestorCategorias.buscarTodas();
		}
		catch(NoResultException e){
			throw new NoResultException("No existen categorías");
		}
		request.setAttribute("listaDeCategorias", categoriasBBDD);
		request.setAttribute("productoModificar", productoBBDD);
	}

}


	

