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
 * Servlet implementation class ServletAdministrador
 */
@WebServlet("/ServletAdministrador")
public class ServletAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// inicializarAdministradorService
	private AdministradorService serviceAdministrador = new AdministradorService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAdministrador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// variable
		String tipo = request.getParameter("tipo");
		
		if (tipo.equals("listar")) {
			listar(request, response);
		} else if (tipo.equals("buscar")){
			buscar(request, response);
		} else if (tipo.equals("registrar")) {
			registrar(request, response);
		} else if (tipo.equals("actualizar")) {
			actualizar(request, response);
		} else if (tipo.equals("eliminar")) {
			eliminar(request, response);
		}
	}
	
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("data", serviceAdministrador.listarAdministrador());
		request.getRequestDispatcher("listarAdministradores.jsp").forward(request, response);
	}
	
	protected void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int cod = Integer.parseInt(request.getParameter("cod"));
		request.setAttribute("administrador", serviceAdministrador.buscarAdministrador(cod));
		request.getRequestDispatcher("actualizarAdministrador.jsp").forward(request, response);
	}
	
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nombre = request.getParameter("txtNom");
		String apellido = request.getParameter("txtApe");
		String dni = request.getParameter("txtDni");
		String genero = request.getParameter("txtGen").toUpperCase();
		String fecha = request.getParameter("txtFec");
		String email = request.getParameter("txtEma");
		String telefono = request.getParameter("txtTel");
		String direccion = request.getParameter("txtDir");
		String usuario = request.getParameter("txtUsu");
		String contra = request.getParameter("txtCon");
		
		AdministradorDTO objAdmin = new AdministradorDTO();
		objAdmin.setNomAdmin(nombre);
		objAdmin.setApeAdmin(apellido);
		objAdmin.setDniAdmin(dni);
		objAdmin.setGenAdmin(genero);
		objAdmin.setFecAdmin(fecha);
		objAdmin.setEmaAdmin(email);
		objAdmin.setTelAdmin(telefono);
		objAdmin.setDirAdmin(direccion);
		objAdmin.setUsuAdmin(usuario);
		objAdmin.setContraAdmin(contra);
		
		int res = serviceAdministrador.registrarAdministrador(objAdmin);
		
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "Los datos del administrador "+ objAdmin.getNomAdmin() + " " + objAdmin.getApeAdmin() + " se registraron correctamente.");
		} else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error en el registro del administrador");
		}
		listar(request, response);
	}
	
	protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int cod = Integer.parseInt(request.getParameter("txtCod"));
		String nombre = request.getParameter("txtNom");
		String apellido = request.getParameter("txtApe");
		String dni = request.getParameter("txtDni");
		String genero = request.getParameter("txtGen").toUpperCase();
		String fecha = request.getParameter("txtFec");
		String email = request.getParameter("txtEma");
		String telefono = request.getParameter("txtTel");
		String direccion = request.getParameter("txtDir");
		String usuario = request.getParameter("txtUsu");
		String contra = request.getParameter("txtCon");
		
		AdministradorDTO objAdmin = new AdministradorDTO();
		objAdmin.setIdAdmin(cod);
		objAdmin.setNomAdmin(nombre);
		objAdmin.setApeAdmin(apellido);
		objAdmin.setDniAdmin(dni);
		objAdmin.setGenAdmin(genero);
		objAdmin.setFecAdmin(fecha);
		objAdmin.setEmaAdmin(email);
		objAdmin.setTelAdmin(telefono);
		objAdmin.setDirAdmin(direccion);
		objAdmin.setUsuAdmin(usuario);
		objAdmin.setContraAdmin(contra);
		
		int res = serviceAdministrador.actualizarAdministrador(objAdmin);
		
		if (res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "Los datos del administrador "+ objAdmin.getNomAdmin() + " " + objAdmin.getApeAdmin() + " se actualizaron correctamente.");
		} else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error en la actualizacion del administrador");
		}
		listar(request, response);
	}
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String dato = request.getParameter("cod");
		int codigo = Integer.parseInt(dato);
		
		AdministradorDTO objAdmin = new AdministradorDTO();
		objAdmin.setIdAdmin(codigo);
		
		// llamarAlServiceEliminar
		int res = serviceAdministrador.eliminarAdministrador(codigo);	
		if (res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "Los datos del administrador ID: " + objAdmin.getIdAdmin() + " se eliminaron correctamente." );
		} else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Para poder eliminar a este administrador, primero elimine los contratos y matriculas que esten a su nombre" );
		}
		listar(request, response);
		
	}

}
