package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdministradorDTO;
import services.AdministradorService;

/**
 * Servlet implementation class ServletLogueo
 */
@WebServlet("/ServletLogueo")
public class ServletLogueo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// inicializarAdministradorService
	private AdministradorService serviceAdministrador = new AdministradorService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogueo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// variables
		String usuario, clave;

		// obtenerParametrosDeLogin.jsp
		usuario = request.getParameter("txtLogin");
		clave = request.getParameter("txtPass");
		
		// obtenerMetodo
		AdministradorDTO admin = serviceAdministrador.iniciarSesion(usuario, clave);
		
		// msjAndRedireccionamiento
		if (admin == null) {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("msj", "Usuario o Contraseña Incorrecta");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if (admin != null) {
			request.setAttribute("adminNombre", admin.getNomAdmin());
			request.setAttribute("adminApellido", admin.getApeAdmin());
			request.setAttribute("adminDni", admin.getDniAdmin());
			request.setAttribute("adminGenero", admin.getGenAdmin().toUpperCase());
			request.setAttribute("adminFecha", admin.getFecAdmin());
			request.setAttribute("adminEmail", admin.getEmaAdmin());
			request.setAttribute("adminTelefono", admin.getTelAdmin());
			request.setAttribute("adminDireccion", admin.getDirAdmin());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} 
	}

}
