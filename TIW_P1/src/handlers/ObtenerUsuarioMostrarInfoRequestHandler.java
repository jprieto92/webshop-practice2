package handlers;

import handlers.ActionHandler;

/**ObtenerUsuarioMostrarInfoRequestHandler --> Se encarga de obtener un usuario
 * para posteriormente mostrar su perfil*/
public class ObtenerUsuarioMostrarInfoRequestHandler extends ActionHandler {

	@Override
	public void execute() throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}
		
		String emailUserInfo;
		
		emailUserInfo = (String) request.getParameter("idUsuario");
		System.out.println("El usuario que se ha seleccionado desde el handler obtenerUsuarioMonstrarInfo tiene el email: "+ emailUserInfo);
		
		//Se añade a la petición el email del usuario a tratar
		request.setAttribute("emailUserModificar", emailUserInfo);
	}

}