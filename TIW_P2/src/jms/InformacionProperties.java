package jms;

import java.util.*;

/**Se encarga de almacenar y proporcionar las funcionalidades para
 * la configuracion con el servidor de JMS*/
public class InformacionProperties {
	private static String strQCF;

	private static String strQueue;

	private static final String nombreProperties = "InfoAplicacion";

	// **************************************************
	public InformacionProperties() {
		super();
	}

	// **************************************************
	public static String getQCF() {

		if (strQCF == null)
			cagarProperties();

		return strQCF;
	}

	// **************************************************
	public static String getQueue() {

		if (strQueue == null)
			cagarProperties();

		return strQueue;
	}

	// **************************************************
	private static void cagarProperties() throws MissingResourceException {

		PropertyResourceBundle appProperties = null;

		try {

			appProperties = (PropertyResourceBundle) PropertyResourceBundle
					.getBundle(nombreProperties);

			strQCF = appProperties.getString("Info.strQCF");
			strQueue = appProperties.getString("Info.strQueue");
			
		} catch (MissingResourceException e) {

			throw e;
		}

	}
	
	
}
