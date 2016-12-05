package servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import handlers.HandlerProxy;


/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerAdminServlet")
@MultipartConfig
public class ControllerAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected javax.servlet.ServletConfig config = null;
	protected javax.servlet.ServletContext sc = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
				
		sc = config.getServletContext();
		this.config = config;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		String action = null;
		//Se recoege la accion enviada desde el cliente
		action = request.getParameter("pAccion");
		
		//Si la accion es nula (primera petición del cliente), se establece la acción indexAdmin
		if(action == null){
			request.getRequestDispatcher("/webViewAdmin/index.jsp").forward(request, response);
		}
		
		//Se llama al manejador de acciones
		HandlerProxy hdlProxy = HandlerProxy.getInstance();
		hdlProxy.creaAction(request, response, action);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}



