package handlers;

import handlers.ActionHandler;

/**ShowFormSentMessageRequestHandler --> Se encarga de obtener los parametros
 * para el formulario de envio de mensaje*/
public class ShowFormSentMessageRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		//Mensaje para pasar entre p�ginas JSP para comunicar el resultado de la acci�n
		String message = (String) request.getAttribute("Message");
		if(message == null){
			message = "";
		}	
		
		//Se recoge el id de producto
		String destinatario= request.getParameter("destinatario");
		
		//Se a�aden los atributos que el formulario enviarMensaje.jsp necesitar�
		request.setAttribute("destinatario", destinatario);
	}

}
