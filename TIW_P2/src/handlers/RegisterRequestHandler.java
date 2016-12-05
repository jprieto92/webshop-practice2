package handlers;

import javax.servlet.http.Part;

import entitiesJPA.TipoUsuario;
import entitiesJPA.Usuario;
import entityManagers.UserManager;

//Manejador de la accion "registro".
public class RegisterRequestHandler extends ActionHandler {
	public void execute () throws Exception {

		//Creamos un entidad con TipoUsuario con ID 1, que user normal
		TipoUsuario tipoUsuario = new TipoUsuario();
		tipoUsuario.setId_tipoUsuario(1);
		
		//Rellenamos la entidad usuario con los datos proporcionados en el formulario y datos de control
		Usuario usuarioAInsertar  = new Usuario();
		
		//Se recogen las imagenes
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
		usuarioAInsertar.setContraseña((String)request.getParameter("pass"));
		usuarioAInsertar.setTelefono(Integer.parseInt(request.getParameter("phone")));
		usuarioAInsertar.setFechaAlta(new java.util.Date());
		usuarioAInsertar.setTipoUsuario(tipoUsuario);

		//Gestora de la persistencia de los datos de usuario
		UserManager gestorDatos = new UserManager();
		String message = "";
		try {
			message = gestorDatos.insertar(usuarioAInsertar);
			request.setAttribute("indexMessage", message);
		}catch(Exception e){
			e.printStackTrace();			
			request.setAttribute("indexMessage", message);
			//Hay que lanzar una excepcion, para saber que no se ha insertado y asi mandarle a otro manejador distinto
			throw new Exception("Error en la creacion del usuario");
		}

	}
}