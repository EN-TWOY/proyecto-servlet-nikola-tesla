package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MatriculaDTO;
import services.MatriculaService;

@WebServlet("/ServletMatricula")
public class ServletMatricula extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MatriculaService serviMatricula = new MatriculaService();

    public ServletMatricula() {
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
			eliminar(request,response);
		}
	}


	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("data", serviMatricula.listaMatriculas());
		request.getRequestDispatcher("listarMatriculas.jsp").forward(request, response);
	}
	
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("cod"));
		request.setAttribute("matricula", serviMatricula.buscaCodigo(cod));
		request.getRequestDispatcher("actualizarMatricula.jsp").forward(request, response);
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fecha = request.getParameter("txtFec");
		int administrador = Integer.parseInt(request.getParameter("cboAdm"));
		int estudiante = Integer.parseInt(request.getParameter("cboEst"));
		int carrera = Integer.parseInt(request.getParameter("cboCar"));
		int turno = Integer.parseInt(request.getParameter("cboTur"));
		int sede = Integer.parseInt(request.getParameter("cboSed"));
		
		MatriculaDTO obj = new MatriculaDTO();
		obj.setFecMatricula(fecha);
		obj.setCodAdmin(administrador);
		obj.setCodEstudiante(estudiante);
		obj.setCodCarrera(carrera);
		obj.setCodTurno(turno);
		obj.setCodSede(sede);
		
		int res = serviMatricula.registraMatricula(obj);
		
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "Una nueva matricula fue registrada con la siguiente fecha: " + obj.getFecMatricula());
		}
		else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error inesperado al registrar neuva matricula");
		}
		listar(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int codigo = Integer.parseInt(request.getParameter("txtCod"));
		String fecha = request.getParameter("txtFec");
		int administrador = Integer.parseInt(request.getParameter("cboAdm"));
		int estudiante = Integer.parseInt(request.getParameter("cboEst"));
		int carrera = Integer.parseInt(request.getParameter("cboCar"));
		int turno = Integer.parseInt(request.getParameter("cboTur"));
		int sede = Integer.parseInt(request.getParameter("cboSed"));
		
		MatriculaDTO obj = new MatriculaDTO();
		obj.setNumMatricula(codigo);
		obj.setFecMatricula(fecha);
		obj.setCodAdmin(administrador);
		obj.setCodEstudiante(estudiante);
		obj.setCodCarrera(carrera);
		obj.setCodTurno(turno);
		obj.setCodSede(sede);
		
		int res = serviMatricula.actualizaMatricula(obj);
		
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "La matricula con el codigo: " + obj.getNumMatricula() + " fue actualizado hoy ");
		}
		else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error inesperado al actualizar esta matricula");
		}
		listar(request, response);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("cod"));
		int res = serviMatricula.eliminaMatricula(codigo);
		
		MatriculaDTO obj = new MatriculaDTO();
		obj.setNumMatricula(codigo);
		
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "La matricula con ID: " + obj.getNumMatricula() + " fue eliminada exitosamente");
		}
		else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error inesperado al eliminar esta matricula");
		}
		listar(request, response);
	}

}
