package handlers;

import javax.persistence.NoResultException;
import javax.servlet.http.Part;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import entitiesJPA.Categoria;
import entitiesJPA.Producto;
import entitiesJPA.ProductoCrear;
import entityManagers.CategoriaManager;

/**UpdateProductRequestHandler --> Se encarga de actualizar un producto*/
public class UpdateProductRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recogen los datos del formulario
		int idP= Integer.parseInt(request.getParameter("idProducto"));
		String tituloNuevo = request.getParameter("tituloProducto");
		String descripcionNueva = request.getParameter("descripcionProducto");
		Part filePart = request.getPart("imagen1Producto");
		int idCategoria= Integer.parseInt(request.getParameter("categoriaProducto"));
		
		
		//Se establecen los nuevos valores del producto
		
		ProductoCrear productoModificado = new ProductoCrear();
		
		//Se comprueba si se ha modificado la imagen
		if(filePart.getSize() != 0){

			byte[] data = new byte[(int) filePart.getSize()];
			filePart.getInputStream().read(data, 0, data.length);
			productoModificado.setImagen(data);
		}
		productoModificado.setProductId(idP);
		productoModificado.setTitulo(tituloNuevo);
		productoModificado.setDescripccion(descripcionNueva);
		productoModificado.setPrecio(Integer.parseInt(request.getParameter("precioProducto")));
		productoModificado.setPrecioNegociable((String) request.getParameter("precioNegociable"));
		productoModificado.setEnvios((String) request.getParameter("realizaEnviosProducto"));
		productoModificado.setIdCategoria(idCategoria);

		//REST Client using PUT Verb and JSON
		Client client = ClientBuilder.newClient();
		Producto productoInsertado = null;

		try {
			WebTarget webResource = client.target("http://localhost:8020").path("productos");
			productoInsertado =	webResource.request("application/json").accept("application/json").put(Entity.entity(productoModificado,MediaType.APPLICATION_JSON),Producto.class);

		}catch(WebApplicationException e){
			message = message+" "+e.getMessage()+".";
			throw e;		
		}
		catch(Exception e){
			throw e;
		}
		finally{
			request.setAttribute("Message", message);
		}
	}

}