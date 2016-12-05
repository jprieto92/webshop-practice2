package handlers;

import handlers.ActionHandler;

/**ShowFormSentMessageRequestHandler --> Se encarga de obtener los parametros
 * para el formulario de envio de mensaje*/
public class ShowFormSentMessageRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}	
		
		//Se recoge el id de producto
		String destinatario= request.getParameter("destinatario");
		
		//Se añaden los atributos que el formulario enviarMensaje.jsp necesitará
		request.setAttribute("destinatario", destinatario);
	}

}
