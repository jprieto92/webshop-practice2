package handlers;

import javax.servlet.http.Part;

import entitiesJPA.TipoUsuario;
import entitiesJPA.Usuario;
import entityManagers.UserManager;

/**RegisterRequestHandler --> Manejador de la accion de registro*/
public class RegisterRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
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
		usuarioAInsertar.setContraseña((String)request.getParameter("passHashRegister"));
		usuarioAInsertar.setTelefono(Integer.parseInt(request.getParameter("phone")));
		usuarioAInsertar.setFechaAlta(new java.util.Date());
		usuarioAInsertar.setTipoUsuario(tipoUsuario);

		//Gestora de la persistencia de los datos de usuario
		UserManager gestorDatos = new UserManager();
		try {
			message = message+" "+gestorDatos.insertar(usuarioAInsertar)+".";
		}catch(Exception e){
			message = message+" "+"Error en la creacion del usuario"+".";
			throw new Exception(message);
		}
		finally{
			request.setAttribute("Message", message);
		}

	}
}