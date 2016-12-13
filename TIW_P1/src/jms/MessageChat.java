package jms;

import java.io.Serializable;

/**MessageChat --> Será el objecto que se envia a la cola
 * JMS como mensaje, teniendo autor del mensaje, receptor
 * y el mensaje que se quiere enviar*/
public class MessageChat implements Serializable {
	private String author;	 // Autor del mensaje
	private String receiver; // Receptor destinatario del mensaje
	private String text;	 // Texto del mensaje

	//Crea el objeto mensaje
	public MessageChat(String author, String receiver, String text)
	{
		this.author = author;
		this.receiver = receiver;
		this.text = text;
	}
	
	public MessageChat()
	{
		
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void escrituraJMS(MessageChat mensaje)
	{
		
		
	}
	
	public MessageChat lecturaJMS(String destinatario){
		MessageChat mensaje = null;
		
		return mensaje;
	}
	
	
}