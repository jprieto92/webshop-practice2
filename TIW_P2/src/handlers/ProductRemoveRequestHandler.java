package handlers;

import javax.persistence.NoResultException;
import entityManagers.ProductManager;

/**ProductRemoveRequestHandler --> Se encarga de eliminar un producto de la 
 * base de datos*/
public class ProductRemoveRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {		
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recupera el id del producto
		Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
		
		//Se borra el producto de la BBDD
		ProductManager gestorDatos = new ProductManager();
		try {
			message = message+" "+gestorDatos.darDeBaja(idProducto)+".";
		}catch(Exception e){
			message = message+" "+e.getMessage()+".";
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}	
	}

}
