package handlers;

import java.util.List;

import javax.persistence.NoResultException;

import entitiesJPA.Categoria;
import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entityManagers.CategoriaManager;
import entityManagers.DisponibilidadManager;
import entityManagers.ProductManager;

public class ShowFormChangeAvailabilityRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		request.setAttribute("createProductMessage", "Si est�s leyendo esto, es porque la petici�n ha sido leida por el manejador de ShowFormCreateProductHandler.");
		
		//Se recoge la idProducto de la peticion
		int idProducto = Integer.parseInt(request.getParameter("idProducto"));

		//Se obtiene la categoria del producto por su idProducto
		ProductManager productManager = new ProductManager();
		Disponibilidad disponibilidadProducto;
		try{
			disponibilidadProducto =  productManager.buscarDisponibilidadPorId(idProducto);
		}
		catch(NoResultException e){
			throw new NoResultException("Error al obtener la disponiblidad del producto. No existe el producto");
		}	
		//Se pasar�n las categor�as que debe mostrar en el formulario, cargadas de la BBDD
		DisponibilidadManager gestorDisponibilidades = new DisponibilidadManager();
		List<Disponibilidad> disponibilidadesBBDD;
		try{
			disponibilidadesBBDD =  gestorDisponibilidades.buscarTodas();
		}
		catch(NoResultException e){
			throw new NoResultException("No existen disponibilidades");
		}		
		
		request.setAttribute("listaDeDisponibilidades", disponibilidadesBBDD);
		request.setAttribute("idProducto", idProducto);
		request.setAttribute("disponibilidadProducto", disponibilidadProducto);
	}

}