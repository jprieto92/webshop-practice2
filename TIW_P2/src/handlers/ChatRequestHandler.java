package handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import jms.InteraccionMQ;
import jms.MessageChat;

/**ChatRequestHandler --> Manejador que obtiene y consume de la cola JMS
 * todos los mensajes asociados a una conversacion*/
public class ChatRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		//Mensaje para pasar entre páginas JSP para comunicar el resultado de la acción
		String message = (String) request.getAttribute("Message");		
		if(message == null){
			message = "";
		}
		
		/*Obtenemos todos los mensajes asociados a una conversacion*/
		HttpSession sesion = request.getSession(false);
		String usuarioSession = (String) sesion.getAttribute("userEmailSession");
		String conversacion = (String) request.getParameter("conversacion");
		System.out.println("ID : " + conversacion);
		InteraccionMQ mq = new InteraccionMQ();
		List<MessageChat> listaMensajes = new ArrayList<MessageChat>();
		/*Consumimos los mensajes de la conversacion de la cola*/
        listaMensajes = mq.lecturaMQ(conversacion);
		System.out.println("MENSAJES QUE ESTOY RECIBIENDO : ---" + listaMensajes.size());
		/*Aquí debemos de leer de la cola de JMS para enviarle al form los mensajes recibidos*/
		
		
		request.setAttribute("mensajesRecibidos", listaMensajes);
	}

}
