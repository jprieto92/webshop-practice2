package handlers;



import javax.servlet.http.Part;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import entitiesJPA.Usuario;


/**ModificarUsuarioRequestHandler --> Se encarga de modificar el usuario*/
public class ModificarUsuarioRequestHandler extends ActionHandler {

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}

		//Se recupera el email del usuario a modificar
		String emailUsuario =  (String) request.getAttribute("emailUserModificar");

		//Recogemos los datos del formulario
		String nuevaContrase�a = request.getParameter("passHashRegister");
		String nuevoNombre = request.getParameter("name");
		String nuevoApellido1 = request.getParameter("apellido1");
		String nuevoApellido2 = request.getParameter("apellido2");
		String nuevaCiudad = request.getParameter("ciudad");
		Integer nuevoTelefono =  Integer.parseInt(request.getParameter("phone")) ;

		Usuario usuarioModificado = new Usuario();
		
		//Si la imagen no ha variado, no se modifica
		Part filePart = request.getPart("imagenPerfil");
		if(filePart.getSize() != 0){			
			byte[] data = new byte[(int) filePart.getSize()];
			filePart.getInputStream().read(data, 0, data.length);
			usuarioModificado.setImagenPerfil(data);;
		}
		//El resto de parametros siempre se modifican, puesto que los campos ya tienen un valor por defecto
		usuarioModificado.setNombre(nuevoNombre);
		usuarioModificado.setApellido1(nuevoApellido1);
		usuarioModificado.setApellido2(nuevoApellido2);
		usuarioModificado.setCiudad(nuevaCiudad);
		usuarioModificado.setTelefono(nuevoTelefono);
		usuarioModificado.setContrase�a(nuevaContrase�a);
		usuarioModificado.setEmail(emailUsuario);
		
		//REST Client using PUT Verb and JSON
		Client client = ClientBuilder.newClient();
		Usuario usuarioInsertado = null;
		
		/* L�nea para provocar la excepci�n NotFoundException */
//		usuarioModificado.setEmail("dasdsadas");
		
		try {
			WebTarget webResource = client.target("http://localhost:8010").path("usuarios");
			usuarioInsertado =	webResource.request("application/json").accept("application/json").put(Entity.entity(usuarioModificado,MediaType.APPLICATION_JSON),Usuario.class);
			
			message = message+" "+"El usuario con email "+usuarioInsertado.getEmail()+" se ha modificado correctamente.";	
		}
		/* Se captura el error HTTP 404 a trav�s de la excepci�n NotFoundException */
		catch(NotFoundException e){
				message = "No existe el usuario con email "+usuarioModificado.getEmail()+".";
		}
		catch(Exception e){
				message = "Se ha producido un error modificando el usuario con email "+usuarioModificado.getEmail()+".";
		}
		finally{
			request.setAttribute("Message", message);
		}

	}

}
