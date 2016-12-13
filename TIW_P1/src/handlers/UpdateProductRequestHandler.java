package handlers;

import javax.persistence.NoResultException;
import javax.servlet.http.Part;
import entitiesJPA.Categoria;
import entitiesJPA.Producto;
import entityManagers.CategoriaManager;
import entityManagers.ProductManager;

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
		
		//Se busca el producto en la BBDD
		ProductManager productManager = new ProductManager();
		Producto productoBBDD = null;
		try{
			productoBBDD = productManager.buscarPorId(idP);
		}
		catch(NoResultException e){
			message = message+" "+"Error en la modificación del producto"+".";
			throw new NoResultException(message);
 		}
		finally{
			request.setAttribute("Message", message);
 		}
		
		//Se busca la categoría en la BBDD
		CategoriaManager categoriaManager= new CategoriaManager();
		Categoria categoria = null;
		try{
			categoria = categoriaManager.buscarPorId(idCategoria);
		}
		catch(Exception e){
			message = message+" "+e.getMessage()+".";
			throw new Exception(message);
 		}
		finally{
			request.setAttribute("Message", message);
 		}
		
		//Se establecen los nuevos valores del producto
		if(filePart.getSize() != 0){
			
			byte[] data = new byte[(int) filePart.getSize()];
			filePart.getInputStream().read(data, 0, data.length);
//			productoBBDD.setImagen(data);
		}
		productoBBDD.setTitulo(tituloNuevo);
		productoBBDD.setDescripccion(descripcionNueva);
		productoBBDD.setPrecio(Integer.parseInt(request.getParameter("precioProducto")));
		productoBBDD.setPrecioNegociable((String) request.getParameter("precioNegociable"));
		productoBBDD.setEnvios((String) request.getParameter("realizaEnviosProducto"));
		productoBBDD.setCategoria(categoria);
		
		
		//Se actualiza el producto en la BBDD
		try{
			message = productManager.modificar(productoBBDD);
		}
		catch(Exception e){
			message = message+" "+"Error en la actualización del producto"+".";
			throw new Exception(message);
 		}
		finally{
			request.setAttribute("Message", message);
 		}

	}

}