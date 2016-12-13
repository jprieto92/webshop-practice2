package handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**ActionHandler --> Clase abstracta de quien hereda el resto de handlers
 * para conducir la navegacion por la web*/
public abstract class ActionHandler {

	protected HttpServletRequest request; 
	protected HttpServletResponse response;
	
	public void setRequest(HttpServletRequest request){
		this.request = request; 
	}
	public void setResponse(HttpServletResponse response){
		this.response = response; 
	}
	
	//Se proporciona a todas las clases hijas los datos de la petición del cliente
	public void setActionParams (HttpServletRequest request, HttpServletResponse response ){
		this.request = request; 
		this.response = response; 
	}
	//Método abstracto que deberán implementar todas las clases hijas
	public abstract void execute() throws Exception;
	
	//Método para efectuar el poliformismo de la clase AcctionHandler
	public boolean executeAction() { 
		
		boolean resp = true;
		
		try{ 
			execute();
		} catch (Throwable ex) 
		{
			System.out.println(" Exception: " + ex.toString() + " en " + getClass() );
			resp = false; 
		}
		return resp;
	}
	
	
	
}
