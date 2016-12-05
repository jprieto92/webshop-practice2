package handlers;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;

import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entityManagers.DisponibilidadManager;
import entityManagers.ProductManager;

public class ChangeAvailabilityRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		request.setAttribute("createProductMessage", "Si estás leyendo esto, es porque la petición ha sido leida por el manejador de ShowFormCreateProductHandler.");
		
		Integer idNuevaDisponibilidad = Integer.parseInt(request.getParameter("disponibilidadProducto"));
		Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
		
		//Variable donde imprimiremos el mensaje de respuesta, ya sea de error o satisfactorio
		String message = "";
		
		//Se obtiene el producto a modificar de la BBDD
		ProductManager productManager= new ProductManager();
		Producto productoBBDD = null;
		try{
			productoBBDD =  productManager.buscarPorId(idProducto);
		}
		catch(NoResultException e){
			throw new NoResultException("No existe el producto con el id "+idProducto);
		}
		
		//Se obtiene la disponibilidad de la BBDD
		DisponibilidadManager disponiblidadManager= new DisponibilidadManager();
		Disponibilidad disponibilidadBBDD = null;
		try{
			disponibilidadBBDD =  disponiblidadManager.buscarPorId(idNuevaDisponibilidad);
		}
		catch(NoResultException e){
			throw new NoResultException("No existe la disponibilidad con el id "+idNuevaDisponibilidad);
		}
		
		//Se cambia la disponiblidad del producto
		productoBBDD.setDisponibilidad(disponibilidadBBDD);
		
		//Se actualiza el producto en la BBDD	
		try {
			message = productManager.modificar(productoBBDD);
		}catch(RollbackException e){
			//Hay que lanzar una excepcion, para saber que no se ha modificado y asi mandarle a otro manejador distinto
			message = "Error en la modificación del producto con id "+idProducto;
			throw new Exception("Error en la modificación del producto");
		}
		finally{
			request.setAttribute("Message", message);
 		}
		
		
	}

}