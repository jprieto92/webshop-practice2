package jms;

import java.io.Serializable;

public class MessageChat implements Serializable {
	private String author;	 // Autor del mensaje
	private String receiver; // Receptor destinatario del mensaje
	private String reason;	 // Motivo del mensaje
	private String text;	 // Texto del mensaje

	//Crea el objeto mensaje
	public MessageChat(String author, String receiver, String text)
	{
		this.author = author;
		this.receiver = receiver;
		this.text = text;
		this.reason="";
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

	public String getReason() {
		// Si el motivo es de administrador, desmontamos
		String auxReason="";
		if((reason.split(":"))[0]=="admin")
		{
			auxReason=(reason.split(":"))[1];
			return auxReason;
		}
		// Si el motivo es de producto, devolvemos motivo
		return reason;
	}

	public void setReason(String reason, String type) {
		// Si motivo es de administrador, montamos motivo
		String auxReason="";
		if (type.equals("admin"))
		{
			auxReason="admin:" + reason;
			this.reason = auxReason;
		}else{
			this.reason = reason;	
		}
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