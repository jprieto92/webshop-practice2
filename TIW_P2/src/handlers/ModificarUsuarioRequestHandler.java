package handlers;


import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import entitiesJPA.Usuario;
import entityManagers.UserManager;

public class ModificarUsuarioRequestHandler extends ActionHandler {

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		
		
		//Recogemos los datos del formulario
		String nuevaContraseña = request.getParameter("pass");
		String nuevoNombre = request.getParameter("name");
		String nuevoApellido1 = request.getParameter("apellido1");
		String nuevoApellido2 = request.getParameter("apellido2");
		String nuevaCiudad = request.getParameter("ciudad");
		Integer nuevoTelefono =  Integer.parseInt(request.getParameter("phone")) ;
		
		//Recuperamos el email del usuario de la sesion
 		HttpSession session = request.getSession(false);
		Usuario usuarioSession = (Usuario) session.getAttribute("entityUser");
		
		//Buscamos al usuario en la BBDD
		String message = "";
		UserManager userManager = new UserManager();
		Usuario usuarioBBDD = null;
		try{
			usuarioBBDD = userManager.buscarPorEmail(usuarioSession.getEmail());
		}
		catch(NoResultException e){
			message = "Error en la modificación del usuario";
			throw new NoResultException("No se ha encontrado el usuario en la BBDD con ese email");
 		}
		finally{
			request.setAttribute("Message", message);
 		}
		
		//Actualizamos los datos del usuarioBBDD acorde a las modificaciones solicitadas
		if(nuevaContraseña!= null){
			usuarioBBDD.setContraseña(nuevaContraseña);
		}
		Part filePart = request.getPart("imagenPerfil");
		if(filePart.getSize() != 0){			
			byte[] data = new byte[(int) filePart.getSize()];
			filePart.getInputStream().read(data, 0, data.length);
			usuarioBBDD.setImagenPerfil(data);;
		}
		usuarioBBDD.setNombre(nuevoNombre);
		usuarioBBDD.setApellido1(nuevoApellido1);
		usuarioBBDD.setApellido2(nuevoApellido2);
		usuarioBBDD.setCiudad(nuevaCiudad);
		usuarioBBDD.setTelefono(nuevoTelefono);
		
		//Actualizamos el usuario en la BBDD
		try{
			message = userManager.modificar(usuarioBBDD);
		}
		catch(Exception e){
			message = "Error en la modificación del usuario";
			throw new Exception("Error en la modificación del usuario al insertarlo en la BBDD");
 		}
		finally{
			request.setAttribute("Message", message);
 		}
		
		//Si todo ha ido bien, actualizamos el usuario de la session con el usuario enviado a la BBDD
		session.setAttribute("entityUser", usuarioBBDD);
		
	}

}
