package handlers;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entityManagers.DisponibilidadManager;
import entityManagers.ProductManager;

/**ChangeAvailabilityRequestHandler --> Manejador que cambia la disponibilidad
 * de un producto*/
public class ChangeAvailabilityRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		Integer idNuevaDisponibilidad = Integer.parseInt(request.getParameter("disponibilidadProducto"));
		Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
		
		//Se obtiene el producto a modificar de la BBDD
		ProductManager productManager= new ProductManager();
		Producto productoBBDD = null;
		try{
			productoBBDD =  productManager.buscarPorId(idProducto);
		}
		catch(NoResultException e){
			message = message+" "+"No existe el producto con el id "+idProducto+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
 		}
		
		//Se obtiene la disponibilidad de la BBDD
		DisponibilidadManager disponiblidadManager= new DisponibilidadManager();
		Disponibilidad disponibilidadBBDD = null;
		try{
			disponibilidadBBDD =  disponiblidadManager.buscarPorId(idNuevaDisponibilidad);
		}
		catch(NoResultException e){
			message = message +" "+"No existe la disponibilidad con el id "+idNuevaDisponibilidad+".";
			throw new NoResultException(message);
		}
		finally{
			request.setAttribute("Message", message);
 		}
		//Se cambia la disponiblidad del producto
		productoBBDD.setDisponibilidad(disponibilidadBBDD);
		
		//Se actualiza el producto en la BBDD	
		try {
			message = productManager.modificar(productoBBDD);
		}catch(RollbackException e){
			//Hay que lanzar una excepcion, para saber que no se ha modificado y asi mandarle a otro manejador distinto
			message = message+" "+"Error en la modificación del producto con id "+idProducto+".";
			throw new Exception(message);
		}
		finally{
			request.setAttribute("Message", message);
 		}
		
		
	}

}