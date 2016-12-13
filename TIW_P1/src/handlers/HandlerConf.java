package handlers;

import java.util.ResourceBundle;

/**HandlerConf --> Se encarga de controlar la carga de las propiedades
 * del proyecto para guiarse en la navegacion*/
public class HandlerConf {
	
	private String propertiesPath;
	
	public String getPropertiesPath() { return propertiesPath; }
	
	public void setPropertiesPath(String propPath) { 
		
		this.propertiesPath = propPath;
	
	}	
	private ResourceBundle props = null;
	
	public String getProperty(String ppropertyKey) 
	{ 
		/*Obtenemos las properties del proyecto*/
		props = ResourceBundle.getBundle(getPropertiesPath() ); 
		
		System.out.println(" pidiendo la propiedad  " + ppropertyKey );
		System.out.println(" valor de la propiedad  " + this.props.getString(ppropertyKey) );
		
		return this.props.getString(ppropertyKey); 
	}
	
	
}
