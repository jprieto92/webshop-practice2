package handlers;

import javax.persistence.NoResultException;

import entitiesJPA.Producto;
import entitiesJPA.Usuario;
import entityManagers.ProductManager;
import handlers.ActionHandler;
import jms.MessageChat;

public class SentMessageRequestHandler extends ActionHandler {
	public void execute () throws Exception {
		request.setAttribute("createProductMessage", "Si estás leyendo esto, es porque vamos a escribir en la cola.");
		String idProducto= request.getParameter("producto");
		String destinatario= request.getParameter("destinatario");
		String emisor= request.getParameter("emisor");
		String mensaje= request.getParameter("mensaje");
		MessageChat mensajejms = new MessageChat(emisor, destinatario, mensaje);
		//mensajejms.escrituraJMS(mensajejms);
	    System.out.println("El mensaje a enviar es: " +  mensaje);
	}

}
