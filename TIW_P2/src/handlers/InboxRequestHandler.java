package handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import jms.InteraccionMQ;
import jms.MessageChat;

/**InboxRequestHandler --> Se encarga de obtener las conversaciones
 * pendientes para mostrar el indice en la bandeja de entrada*/
public class InboxRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");		
		if(message == null){
			message = "";
		}
		/*Obtenemos los chats que tenemos pendientes
		 * obteniendo los usuarios que nos han enviado mensajes*/
		HttpSession sesion = request.getSession(false);
		String usuarioSession = (String) sesion.getAttribute("userEmailSession");
		InteraccionMQ mq = new InteraccionMQ();
		List<String> nuevasConversaciones = new ArrayList<String>();
		/*Buscamos en la cola sin consumir*/
		nuevasConversaciones = mq.buscarConversaciones(usuarioSession);
		if(nuevasConversaciones!=null && nuevasConversaciones.size()>0)
		{
			System.out.println("CONVERSACIONES QUE ESTOY RECIBIENDO EN INBOX : ---" + nuevasConversaciones.size());
		}
		/*Aquí debemos de leer de la cola de JMS para enviarle al form los mensajes recibidos*/
		request.setAttribute("conversacionesNuevas", nuevasConversaciones);
	}

}
