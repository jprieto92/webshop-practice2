package handlers;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import entitiesJPA.Categoria;
import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.CategoriaManager;
import entityManagers.ProductManager;


public class CreateProductRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
 		//Se recupera la sesión de usuario
 		HttpSession session = request.getSession(false);
 		
 		//Rellenamos la entidad producto con los datos proporcionados en el formulario y otros datos en función de la sesión y la fecha del sistema
		Producto productoAInsertar  = new Producto();

		productoAInsertar.setDescripccion((String) request.getParameter("descripcion"));
		productoAInsertar.setEnvios((String) request.getParameter("realizaEnviosProducto"));
		productoAInsertar.setFechaPublicacion(new java.util.Date());
		
		
		//Se recogen las imagenes
		Part filePart = request.getPart("imagen1Producto");
		// El tamaño de un array en Java es máximo Integer.maxValue por lo tanto la manera que lo
		// he hecho tenemos una limitación de maximo de 2 GB en el fichero si tiene que ser más grande
		// hay que buscar otra manera.
	    byte[] data = new byte[(int) filePart.getSize()];
	    filePart.getInputStream().read(data, 0, data.length);
		productoAInsertar.setImagen(data);
		
		productoAInsertar.setPrecio(Integer.parseInt(request.getParameter("precioProducto")));
		productoAInsertar.setPrecioNegociable((String) request.getParameter("precioNegociable"));
		productoAInsertar.setTitulo((String) request.getParameter("tituloProducto"));
		//Se establece a quien pertenece el producto a través de la sesión recuperada
		productoAInsertar.setUsuario((Usuario) session.getAttribute("entityUser"));
		
		
		//Se crea la disponibilidad por defecto ("DISPONIBLE")
		Disponibilidad disponibilidad = new Disponibilidad();
		disponibilidad.setIdDisponibilidad(1);
		productoAInsertar.setDisponibilidad(disponibilidad);

		//Se asocia con la categoria (por ahora la ponemos por defecto de prueba)
		CategoriaManager categoriaManager= new CategoriaManager();
		int idCategoria= Integer.parseInt(request.getParameter("categoriaProducto"));
		Categoria categoria = categoriaManager.buscarPorId(idCategoria);		
		//categoria.setIdCategoria(idCategoria);
		productoAInsertar.setCategoria(categoria);
		

		//Gestora de la persistencia de los datos de producto
		ProductManager gestorDatos = new ProductManager();
		try {
			gestorDatos.insertar(productoAInsertar);
		}catch(RollbackException e){
			//Hay que lanzar una excepcion, para saber que no se ha insertado y asi mandarle a otro manejador distinto
			request.setAttribute("createProductMessage", "Ha habido un error insertando el producto");
			throw new Exception("Error en la creacion del producto");
		}

		request.setAttribute("createProductMessage", "El producto "+ (String) request.getParameter("tituloProducto") + " se ha insertado correctamente");
	}

}

