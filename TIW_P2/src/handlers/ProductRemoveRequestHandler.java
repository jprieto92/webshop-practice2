package handlers;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.ProductManager;

public class ProductRemoveRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		
		//Recuperamos el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		Usuario usuarioSession = (Usuario) session.getAttribute("entityUser");
		
		//Recuperamos el id del producto
		Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
		
		//Recuperamos el producto de la BBDD
		ProductManager gestorDatos = new ProductManager();
		Producto producto;
		try {
			producto = gestorDatos.buscarPorId(idProducto);
		}catch(NoResultException e){
			//Hay que lanzar una excepcion, para saber que no se ha insertado y asi mandarle a otro manejador distinto
			request.setAttribute("catalogMessage", "Ha habido un error eliminando el producto");
			throw new NoResultException("El producto con id "+idProducto+" no existe en la BBDD");
		}
		
		//Borramos el producto de la BBDD
		String catalogMessage = "";
		try {
			catalogMessage = gestorDatos.darDeBaja(idProducto);
		}catch(NoResultException e){
			//Hay que lanzar una excepcion, para saber que no se ha insertado y asi mandarle a otro manejador distinto
			request.setAttribute("catalogMessage", "Ha habido un error eliminando el producto");
			throw new NoResultException("El producto con id "+idProducto+" no existe en la BBDD");
		}
		
		//Actualizamos el producto borrado de la session de usuario
		usuarioSession.removeProducto(producto);
		
		request.setAttribute("catalogMessage", catalogMessage);

	}

}
