package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CarreraDTO;
import services.CarreraService;

@WebServlet("/ServletCarrera")
public class ServletCarrera extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CarreraService serviCarrera = new CarreraService();

    public ServletCarrera() {
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
		}else if(xtipo.equals("buscar")) {
			buscar(request, response);
		}else if(xtipo.equals("registrar")) {
			registrar(request, response);
		}else if(xtipo.equals("actualizar")) {
			actualizar(request, response);
		}else if(xtipo.equals("eliminar")) {
			eliminar(request, response);
		}	
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("data", serviCarrera.listaCarreras());
		request.getRequestDispatcher("listarCarreras.jsp").forward(request, response);
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("cod"));
		request.setAttribute("carrera", serviCarrera.buscaCodigo(cod));
		request.getRequestDispatcher("actualizarCarrera.jsp").forward(request, response);
		
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descripcion = request.getParameter("txtDescripcion").toUpperCase();
		
		CarreraDTO objCarrera = new CarreraDTO();
		objCarrera.setDesCarrera(descripcion);

		int res = serviCarrera.registraCarrera(objCarrera);
		if (res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "Los datos descripcion "+ objCarrera.getDesCarrera() + " se registro correctamente.");
		} else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error en el registro de la carrera");
		}
		listar(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("txtCod"));
		String descripcion = request.getParameter("txtDes");
		
		CarreraDTO obj = new CarreraDTO();
		obj.setIdeCarrera(cod);
		obj.setDesCarrera(descripcion);
		
		int res = serviCarrera.actualizaCarrera(obj);
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "Los datos del codigo " + obj.getIdeCarrera() + " se actualizaron correctamente.");
		} else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error en la actualización de la carrera");
		}
		listar(request, response);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("cod"));
		
		CarreraDTO objCarrera = new CarreraDTO();
		objCarrera.setIdeCarrera(cod);
		
		int res = serviCarrera.eliminaCarrera(cod);
		if (res > 0){
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "los datos de la materia ID: " + objCarrera.getIdeCarrera() + " se eliminaron correctamente.");
		} else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error en la eliminacion de la materia.");
		}
		listar(request, response);
	}

}
