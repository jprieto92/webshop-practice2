package handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerProxy {

	private static HandlerProxy hdlProxy;
	
	public HandlerProxy() { }
	
	public static HandlerProxy getInstance(){
		
		if (hdlProxy == null){ 
			hdlProxy = new HandlerProxy(); 
		} 
		return hdlProxy;
		
	} //getInstance
	
	public void creaAction (HttpServletRequest request, HttpServletResponse response, String actionName){
		try{
			
			Boolean nextHandler = false;
		    String nombAcc = actionName;
		    String tipoRespAccExe,valorRespAccExe = null;

		    HandlerConf confAcc = new HandlerConf();
		    confAcc.setPropertiesPath("META-INF.handlers");
		    do {
		    	
		    	String nuevaAcc = confAcc.getProperty(nombAcc + ".srcAction");
		        if ( nuevaAcc != null ){ nuevaAcc = nuevaAcc.trim(); }
		        
		        ActionHandler accion;
				accion = (ActionHandler) Class.forName(nuevaAcc).newInstance();
				accion.setActionParams(request, response);
				Boolean RespAccExe = accion.executeAction();
				
				if (RespAccExe.equals(true))
				{ 
					tipoRespAccExe =  confAcc.getProperty(nombAcc + ".true.resultType");
					if (tipoRespAccExe != null ){ tipoRespAccExe = tipoRespAccExe.trim(); }
					
					valorRespAccExe = confAcc.getProperty(nombAcc+".true.resultValue");
	                if ( valorRespAccExe != null ) { valorRespAccExe = valorRespAccExe.trim(); }
					
					if (tipoRespAccExe.equals("action")) { 

						nextHandler = true;
		                nombAcc = valorRespAccExe;
						
					} else if(tipoRespAccExe.equals("redirect")){
						try { 
							nextHandler = false;
							response.sendRedirect("/"+ valorRespAccExe);
							break;
						} catch (Exception e) {  System.out.println(" error en redirect: " + e.getMessage()); }
					}
					else{
						try { 
							nextHandler = false;
							request.getRequestDispatcher("/"+ valorRespAccExe).forward(request, response);
							break;
						} catch (Exception e) {  System.out.println(" error en forward: " + e.getMessage()); }
					}
					
				} else if (RespAccExe.equals(false)) {
					
					tipoRespAccExe =  confAcc.getProperty(nombAcc + ".false.resultType");
					if (tipoRespAccExe != null ){ tipoRespAccExe = tipoRespAccExe.trim(); }
					
					valorRespAccExe = confAcc.getProperty(nombAcc+".false.resultValue");
	                if ( valorRespAccExe != null ) { valorRespAccExe = valorRespAccExe.trim(); }
					
					if (tipoRespAccExe.equals("action")) { 

						nextHandler = true;
		                nombAcc = valorRespAccExe;
						
					} 
					else if(tipoRespAccExe.equals("redirect")){
						try { 
						nextHandler = false;
						response.sendRedirect("/"+ valorRespAccExe);
						break;
						} catch (Exception e) {  System.out.println(" error en redirect: " + e.getMessage()); }
					}					
					else {
						try { 
							nextHandler = false;
							request.getRequestDispatcher("/"+ valorRespAccExe).forward(request, response);
							break;
						} catch (Exception e) {  System.out.println(" error en forward: " + e.getMessage()); }
					}

				}
		        
		    	
		    } while( nextHandler.equals(true) ) ;


		} catch (Exception ex) {System.out.println(" Exception: " + ex.getMessage() ); }	
		  
	}
	
	
	
	
	
}

