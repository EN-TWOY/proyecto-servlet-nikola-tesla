package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProfesorDTO;
import services.ProfesorService;

@WebServlet("/ServletProfesor")
public class ServletProfesor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProfesorService serviProfesor = new ProfesorService();
       
    public ServletProfesor() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String xtipo = request.getParameter("tipo");
		if(xtipo.equals("listar")) {
			listar(request, response);
		}
		else if(xtipo.equals("buscar")) {
			buscar(request, response);
		}
		else if(xtipo.equals("registrar")) {
			registrar(request, response);
		}
		else if(xtipo.equals("actualizar")) {
			actualizar(request ,response);
		}
		else if(xtipo.equals("eliminar")) {
			eliminar(request, response);
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("data", serviProfesor.listaProfesores());
		request.getRequestDispatcher("listarProfesores.jsp").forward(request, response);
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cod = Integer.parseInt(request.getParameter("cod"));
		request.setAttribute("profesor", serviProfesor.buscaCodigo(cod));
		request.getRequestDispatcher("actualizarProfesor.jsp").forward(request, response);
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre = request.getParameter("txtNom");
		String apellido = request.getParameter("txtApe");
		String dni = request.getParameter("txtDni");
		String genero = request.getParameter("txtGen");
		String nacimiento = request.getParameter("txtFec");
		String email = request.getParameter("txtEma");
		String telefono = request.getParameter("txtTel");
		String direccion = request.getParameter("txtDir");
		
		ProfesorDTO obj = new ProfesorDTO();
		obj.setNompro(nombre);
		obj.setApepro(apellido);
		obj.setDnipro(dni);
		obj.setGenpro(genero);
		obj.setFecpro(nacimiento);
		obj.setEmapro(email);
		obj.setTelpro(telefono);
		obj.setDirpro(direccion);
		
		int res = serviProfesor.registraProfesor(obj);
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "Los datos del empleado "+ obj.getNompro() + " " + obj.getApepro() + " se registraron correctamente.");
		}else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error al registrar nuevo profesor");
		}
		listar(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cod = Integer.parseInt(request.getParameter("txtCod"));
		String nombre = request.getParameter("txtNom");
		String apellido = request.getParameter("txtApe");
		String dni = request.getParameter("txtDni");
		String genero = request.getParameter("txtGen").toUpperCase();
		String nacimiento = request.getParameter("txtFec");
		String email = request.getParameter("txtEma");
		String telefono = request.getParameter("txtTel");
		String direccion = request.getParameter("txtDir");
		
		ProfesorDTO obj = new ProfesorDTO();
		obj.setIdepro(cod);
		obj.setNompro(nombre);
		obj.setApepro(apellido);
		obj.setDnipro(dni);
		obj.setGenpro(genero);
		obj.setFecpro(nacimiento);
		obj.setEmapro(email);
		obj.setTelpro(telefono);
		obj.setDirpro(direccion);
		
		int res = serviProfesor.actualizaProfesor(obj);
		
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "Los datos del codigo " + obj.getIdepro() + " se actualizaron correctamente.");
		} else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error en la actualizacion del profesor");
		}
		listar(request, response);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("cod"));
		
		ProfesorDTO objProfe = new ProfesorDTO();
		objProfe.setIdepro(cod);
		
		int res = serviProfesor.eliminaProfesor(cod);
		
		if (res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "Los datos del administrador ID: " + objProfe.getIdepro() + " se eliminaron correctamente." );
		} else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "No se puede eliminar este profesor por que actualmente tiene un contrato" );
		}
		listar(request, response);
	}

}
