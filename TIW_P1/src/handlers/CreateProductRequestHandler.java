package handlers;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import entitiesJPA.Categoria;
import entitiesJPA.Disponibilidad;
import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.CategoriaManager;
import entityManagers.ProductManager;
import entityManagers.UserManager;

/**CreateProductRequestHandler --> Manejador que controla la creacion
 * de un nuevo producto escribiendo en la base de datos*/
public class CreateProductRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		//Se recupera el email del usuario de la sesion
		HttpSession session = request.getSession(false);
		String emailUsuarioSession =  (String) session.getAttribute("userEmailSession");
 		
 		//Rellenamos la entidad producto con los datos proporcionados en el formulario y otros datos en función de la sesión y la fecha del sistema
		Producto productoAInsertar  = new Producto();

		productoAInsertar.setDescripccion((String) request.getParameter("descripcion"));
		productoAInsertar.setEnvios((String) request.getParameter("realizaEnviosProducto"));
		productoAInsertar.setFechaPublicacion(new java.util.Date());
		

		//Se recogen las imagenes
		Part filePart = request.getPart("imagen1Producto");
	    byte[] data = new byte[(int) filePart.getSize()];
	    filePart.getInputStream().read(data, 0, data.length);
		productoAInsertar.setImagen(data);
		
		productoAInsertar.setPrecio(Integer.parseInt(request.getParameter("precioProducto")));
		productoAInsertar.setPrecioNegociable((String) request.getParameter("precioNegociable"));
		productoAInsertar.setTitulo((String) request.getParameter("tituloProducto"));

		//Se establece a quien pertenece el producto. Para ello se busca en la BBDD
		//Buscamos al usuario en la BBDD
		UserManager userManager = new UserManager();
		Usuario usuarioBBDD = null;
		try{
			usuarioBBDD = userManager.buscarPorEmail(emailUsuarioSession);

		}catch(NoResultException e){
			message = message+" "+e.getMessage()+".";
			throw new NoResultException(e.getMessage());
		}
		finally{
			request.setAttribute("Message", message);
		}
		productoAInsertar.setUsuario(usuarioBBDD);


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
			message = message+". "+gestorDatos.insertar(productoAInsertar);
		}catch(Exception e){
			//Hay que lanzar una excepcion, para saber que no se ha insertado y asi mandarle a otro manejador distinto
			message = message+" "+"Error en la creacion del producto"+".";			
			throw new Exception(message);
		}
		finally{
			request.setAttribute("Message", message);
		}
	}

}

