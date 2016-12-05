package handlers;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import entitiesJPA.Categoria;
import entitiesJPA.Producto;
import entityManagers.CategoriaManager;
import entityManagers.ProductManager;
import entityManagers.UserManager;

public class UpdateProductRequestHandler  extends ActionHandler{

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		//Recogemos los datos del formulario
		int idP= Integer.parseInt(request.getParameter("idProducto"));
		String tituloNuevo = request.getParameter("tituloProducto");
		String descripcionNueva = request.getParameter("descripcionProducto");
		// El tamaño de un array en Java es máximo Integer.maxValue por lo tanto la manera que lo
		// he hecho tenemos una limitación de maximo de 2 GB en el fichero si tiene que ser más grande
		// hay que buscar otra manera.
				
		

//		
//		//Recuperamos el email del usuario de la sesion
 		HttpSession session = request.getSession(false);
		Producto productoModificar = (Producto) request.getAttribute("productoModificar");
//		
//		//Buscamos al usuario en la BBDD
		String message = "";
		ProductManager productManager = new ProductManager();
		Producto productoBBDD = null;
		try{
			productoBBDD = productManager.buscarPorId(idP);
		}
		catch(NoResultException e){
			message = "Error en la modificación del producto";
			throw new NoResultException("No se ha encontrado el producto en la BBDD");
 		}
		finally{
			request.setAttribute("Message", message);
 		}
		//Se recogen las imagenes
		Part filePart = request.getPart("imagen1Producto");
		if(filePart.getSize() != 0){
			
			byte[] data = new byte[(int) filePart.getSize()];
			filePart.getInputStream().read(data, 0, data.length);
			productoBBDD.setImagen(data);
		}
		
		productoBBDD.setTitulo(tituloNuevo);
		productoBBDD.setDescripccion(descripcionNueva);
		productoBBDD.setPrecio(Integer.parseInt(request.getParameter("precioProducto")));
		productoBBDD.setPrecioNegociable((String) request.getParameter("precioNegociable"));
		productoBBDD.setEnvios((String) request.getParameter("realizaEnviosProducto"));
		
		//Se asocia con la categoria (por ahora la ponemos por defecto de prueba)
		//Categoria categoria = new Categoria();
		//categoria.setIdCategoria(1);
		//productoBBDD.setCategoria(categoria);
		//Se asocia con la categoria (por ahora la ponemos por defecto de prueba)
		CategoriaManager categoriaManager= new CategoriaManager();
		int idCategoria= Integer.parseInt(request.getParameter("categoriaProducto"));
		Categoria categoria = categoriaManager.buscarPorId(idCategoria);		
		//categoria.setIdCategoria(idCategoria);
		productoBBDD.setCategoria(categoria);
		
		
//		//Actualizamos el usuario en la BBDD
		try{
			message = productManager.modificar(productoBBDD);
		}
		catch(Exception e){
			message = "Error en la modificación del usuario";
			throw new Exception("Error en la modificación del usuario al insertarlo en la BBDD");
 		}
		finally{
			request.setAttribute("Message", message);
 		}
//		
//		//Si todo ha ido bien, actualizamos el usuario de la session con el usuario enviado a la BBDD
//		session.setAttribute("entityUser", usuarioBBDD);
	}

}