package handlers;

import java.util.List;

import javax.persistence.NoResultException;

import entitiesJPA.Categoria;
import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.CategoriaManager;
import entityManagers.ProductManager;
import entityManagers.UserManager;
import handlers.ActionHandler;

public class ShowFormSentMessageRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		request.setAttribute("createProductMessage", "Si estás leyendo esto, es porque vamos a mandar un mensaje.");
		String idProducto= request.getParameter("idProducto");
		ProductManager productManager = new ProductManager();
		Producto productoBBDD;
		Usuario destinatario;
		try{
			productoBBDD =  productManager.buscarPorId(Integer.parseInt(idProducto));
			destinatario = productoBBDD.getUsuario();
			
		}
		catch(NoResultException e){
			throw new NoResultException("No se puede obtener el propietario o producto");
		}
		request.setAttribute("destinatario", destinatario);
		request.setAttribute("productoMensaje", productoBBDD);
	}

}
