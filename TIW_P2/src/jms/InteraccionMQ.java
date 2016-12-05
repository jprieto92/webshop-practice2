package jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.TextMessage;

import jms.InformacionProperties;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;

/**InteraccionMQ --> Se encarga de la interaccion con la cola JMS
 * escritura - lectura consumiendo - consultar sin consumir*/
public class InteraccionMQ {

	private javax.jms.ConnectionFactory factory = null;
	private javax.naming.InitialContext contextoInicial = null;
	private javax.jms.Destination cola = null;
	private javax.jms.Connection Qcon = null;
	private javax.jms.Session QSes = null;
	private javax.jms.MessageProducer Mpro = null;
	private javax.jms.MessageConsumer Mcon = null;

//	@Resource(mappedName = "jms/cf1.1")
//	private static ConnectionFactory connectionFactory;
//	
//	@Resource(mappedName = "jms/queue1.1")
//	private static Queue queue;

	public void escrituraMQ(MessageChat mensaje, String selector) {

		try {

			contextoInicial = new javax.naming.InitialContext();
			factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
			cola = (javax.jms.Queue) contextoInicial.lookup(InformacionProperties.getQueue());
			Qcon =factory.createConnection();
			QSes = Qcon.createSession(false,javax.jms.QueueSession.AUTO_ACKNOWLEDGE);

			Mpro = QSes.createProducer(cola);

			javax.jms.ObjectMessage men = QSes.createObjectMessage();
			//javax.jms.ObjectMessage men = QSes.createTextMessage();
			men.setObject(mensaje);
			//men.setText(mensaje);
			men.setJMSCorrelationID(selector+":"+mensaje.getAuthor());
			Qcon.start();
			Mpro.send(men);

			this.Mpro.close();
			this.QSes.close();
			this.Qcon.close();
			System.out.println("Mensaje escrito en la cola");

		} catch (javax.jms.JMSException e) {
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
					.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}

	}

	public List<MessageChat> lecturaMQ(String strSelectorPasado) {

		MessageChat mensajeNuevo = new MessageChat();
		List<MessageChat> listaMensajes = new ArrayList<MessageChat>();
		try {
			contextoInicial = new javax.naming.InitialContext();

			factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
			cola = (javax.jms.Queue) contextoInicial.lookup(InformacionProperties.getQueue());
			Qcon = factory.createConnection();			
			QSes = Qcon.createSession(false,javax.jms.QueueSession.AUTO_ACKNOWLEDGE);

			String sSelector = "JMSCorrelationID = '" + strSelectorPasado.trim() + "'";
			Mcon = QSes.createConsumer(cola, sSelector);
			Qcon.start();
			Message mensaje = null;
			System.out.println("VOY A LEER DE LA COLA");
			while (true) {
				mensaje = Mcon.receive(100);
				if (mensaje != null) {
					if (mensaje instanceof ObjectMessage) {
						ObjectMessage m = (ObjectMessage) mensaje;
						System.out.println(m.getObject());
						mensajeNuevo = (MessageChat) m.getObject();
						listaMensajes.add(mensajeNuevo);
						//System.out.println(listaMensajes.getText());
					} else {
						// JHC ************ No es del tipo correcto
						break;
					}
				} else // NO existe mensaje, mensaje es null
				{
					System.out.println("NO HAY MAS MENSAJES POR RECIBIR");
					break;
				}
			}
			this.Mcon.close();
			this.QSes.close();
			this.Qcon.close();
		} catch (javax.jms.JMSException e) {
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
					.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}

		return listaMensajes;

	}
	
	public List<String> buscarConversaciones(String selectorReceptor)
	{
		List<String> listaEmisores = new ArrayList<String>();
		String emisores = "";
		String correlations = "";
		/*------------------------------------
		 * ---------------------------------*/
		try
	    {
			contextoInicial = new javax.naming.InitialContext();
			factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
			cola = (javax.jms.Queue) contextoInicial.lookup(InformacionProperties.getQueue());
			Qcon = factory.createConnection();
			QSes = Qcon.createSession(false,javax.jms.QueueSession.AUTO_ACKNOWLEDGE);
			Enumeration messageEnumeration;
			Message tempMsg;
	      

			QueueBrowser browser = QSes.createBrowser((Queue) cola);
	      
			messageEnumeration = browser.getEnumeration();
			if (messageEnumeration != null)
			{
				if (!messageEnumeration.hasMoreElements())
				{
					System.out.println("There are no messages " + "in the queue.");
				}
				else
				{
					System.out.println("The following messages are in the queue:");
					while (messageEnumeration.hasMoreElements())
					{
						//objectMessage = (ObjectMessage) messageEnumeration.nextElement();
						tempMsg = (Message)messageEnumeration.nextElement();
						System.out.println(" HE ENCONTRADO UN MENSAJE CON ID: " + tempMsg.getJMSCorrelationID());
						correlations = tempMsg.getJMSCorrelationID();
						if(correlations.split(":")[0].equals(selectorReceptor) && (correlations.split(":").length>1) &&  (listaEmisores.contains(correlations)==false))
						{
							listaEmisores.add(correlations);
						}
					}
				}
	      }
	      browser.close();
	      this.QSes.close();
	      this.Qcon.close();
	    } catch (javax.jms.JMSException e) {
			System.out.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}
		/* --------------------------------------------------
		 * --------------------------------------------------*/
		
		
		
		return listaEmisores;
	}
}
