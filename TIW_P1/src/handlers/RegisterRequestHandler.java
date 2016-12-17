package handlers;

import javax.servlet.http.Part;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import entitiesJPA.Usuario;

/**RegisterRequestHandler --> Manejador de la accion de registro*/
public class RegisterRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}

		//Rellenamos la entidad usuario con los datos proporcionados en el formulario y datos de control
		Usuario usuarioAInsertar  = new Usuario();

		//Se recoge la imagen
		Part filePart = request.getPart("imagenPerfil");
		if(filePart.getSize() != 0){			
			byte[] data = new byte[(int) filePart.getSize()];
			filePart.getInputStream().read(data, 0, data.length);
			usuarioAInsertar.setImagenPerfil(data);;
		}	
		
		usuarioAInsertar.setApellido1((String)request.getParameter("apellido1"));
		usuarioAInsertar.setApellido2((String)request.getParameter("apellido2"));
		usuarioAInsertar.setNombre((String)request.getParameter("name"));
		usuarioAInsertar.setCiudad((String)request.getParameter("ciudad"));
		usuarioAInsertar.setEmail((String)request.getParameter("email"));
		usuarioAInsertar.setContraseña((String)request.getParameter("passHashRegister"));
		usuarioAInsertar.setTelefono(Integer.parseInt(request.getParameter("phone")));
		usuarioAInsertar.setFechaAlta(new java.util.Date());
		

		//REST Client using POST Verb and JSON
		Client client = ClientBuilder.newClient();
		Usuario usuarioInsertado = null;

		try {
			WebTarget webResource = client.target("http://localhost:8010").path("usuarios");
			usuarioInsertado =	webResource.request("application/json").accept("application/json").post(Entity.entity(usuarioAInsertar,MediaType.APPLICATION_JSON),Usuario.class);

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