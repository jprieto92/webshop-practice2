package handlers;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;


import entitiesJPA.Producto;
import entitiesJPA.ProductoCrear;


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
		ProductoCrear productoAInsertar  = new ProductoCrear();

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
		productoAInsertar.setEmail(emailUsuarioSession);
		
		//Se establece el id de la categoria
		productoAInsertar.setIdCategoria(Integer.parseInt(request.getParameter("categoriaProducto")));

		
		
		//REST Client using POST Verb and JSON
		Client client = ClientBuilder.newClient();
		Producto productoInsertado = null;

		try {
			WebTarget webResource = client.target("http://localhost:8020").path("productos");
			productoInsertado =	webResource.request("application/json").accept("application/json").post(Entity.entity(productoAInsertar,MediaType.APPLICATION_JSON),Producto.class);

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

