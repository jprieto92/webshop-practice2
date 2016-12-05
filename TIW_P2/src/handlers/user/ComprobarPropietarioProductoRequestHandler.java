package handlers.user;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import entityManagers.ProductManager;
import handlers.ActionHandler;

public class ComprobarPropietarioProductoRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {		
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recupera el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		String emailUsuarioSession =  (String) session.getAttribute("userEmailSession");
		
		//Se recupera el id del producto
		Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
		
		//Se comprueba que el producto pertenece al usuario
		ProductManager gestorDatos = new ProductManager();
		try {
			gestorDatos.comprobarPertenenciaProducto(idProducto, emailUsuarioSession);
		}catch(NoResultException e){
			message = message + " " +e.getMessage() +".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
			
	}

}