package handlers;

import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.ProductManager;

public class MyProductsRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {		
		//Recuperamos el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		Usuario usuarioSession = (Usuario) session.getAttribute("entityUser");
		
		List<Producto> productos;
		ProductManager gestorDatos = new ProductManager();
		try {
			productos = gestorDatos.buscarPorUsuario(usuarioSession);
		}catch(NoResultException e){
			e.printStackTrace();
			//Hay que lanzar una excepcion, para saber que no se ha insertado y asi mandarle a otro manejador distinto
			request.setAttribute("catalogMessage", "Ha habido un error mostrando los productos");
			throw new NoResultException("El usuario "+usuarioSession.getNombre()+" "+usuarioSession.getApellido1()+" "+usuarioSession.getApellido2()+" no tiene productos");
		}

		request.setAttribute("listaDeProductos", productos);
	}

}