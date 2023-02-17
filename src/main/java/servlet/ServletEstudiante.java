package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.EstudianteService;
import beans.EstudianteDTO;

@WebServlet("/ServletEstudiante")
public class ServletEstudiante extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EstudianteService serviEstudiante = new EstudianteService();

    public ServletEstudiante() {
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
			actualizar(request, response);
		}
		else if(xtipo.equals("eliminar")) {
			eliminar(request, response);
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("data", serviEstudiante.listaEstudiantes());
		request.getRequestDispatcher("listarEstudiantes.jsp").forward(request, response);
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cod = Integer.parseInt(request.getParameter("cod"));
		request.setAttribute("estudiante", serviEstudiante.buscaCodigo(cod));
		request.getRequestDispatcher("actualizarEstudiante.jsp").forward(request, response);
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre = request.getParameter("txtNom");
		String apellido = request.getParameter("txtApe");
		String dni = request.getParameter("txtDni");
		String genero = request.getParameter("txtGen");
		String fecha = request.getParameter("txtFec");
		String email = request.getParameter("txtEma");
		String telefono = request.getParameter("txtTel");
		String direccion = request.getParameter("txtDir");
		
		EstudianteDTO obj = new EstudianteDTO();
		obj.setNomEst(nombre);
		obj.setApeEst(apellido);
		obj.setDniEst(dni);
		obj.setGenEst(genero);
		obj.setFecEst(fecha);
		obj.setEmaEst(email);
		obj.setTelEst(telefono);
		obj.setDirEst(direccion);
		
		int res = serviEstudiante.registraEstudiante(obj);
		
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "El nuevo estudiante de nombre: " + obj.getNomEst() + " " + obj.getApeEst() + " fue registrado existosamente");
		}
		else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error al registrar nuevo estudiante");
		}
		listar(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cod = Integer.parseInt(request.getParameter("txtCod"));
		String nombre = request.getParameter("txtNom");
		String apellido = request.getParameter("txtApe");
		String dni = request.getParameter("txtDni");
		String genero = request.getParameter("txtGen");
		String fecha = request.getParameter("txtFec");
		String email = request.getParameter("txtEma");
		String telefono = request.getParameter("txtTel");
		String direccion = request.getParameter("txtDir");
		
		EstudianteDTO obj = new EstudianteDTO();
		obj.setIdeEst(cod);
		obj.setNomEst(nombre);
		obj.setApeEst(apellido);
		obj.setDniEst(dni);
		obj.setGenEst(genero);
		obj.setFecEst(fecha);
		obj.setEmaEst(email);
		obj.setTelEst(telefono);
		obj.setDirEst(direccion);
		
		int res = serviEstudiante.actualizaEstudiante(obj);
		
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "El estudiante con el código " + obj.getIdeEst() + " fue actualizado exitosamente");
		}
		else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error al actualizar estudiante con el codigo " + obj.getIdeEst());
		}
		listar(request, response);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cod = Integer.parseInt(request.getParameter("cod"));
		EstudianteDTO obj = new EstudianteDTO();
		obj.setIdeEst(cod);
		int res = serviEstudiante.eliminaEstudiante(cod);
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "El estudiante fue eliminado correctamente");
		}
		else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Este estudiante es encuentra matriculado para poder eliminarlo debe primero eliminar la matricula");
		}
		listar(request, response);
	}

}
