package handlers;

public class ChatRequestHandler extends ActionHandler {
	
	public void execute () throws Exception {
		request.setAttribute("chatMessage", "Si estás leyendo esto, es porque la petición ha sido leida por el manejador de chat.");
		/*Aquí debemos de leer de la cola de JMS para enviarle al form los mensajes recibidos*/
	}

}
