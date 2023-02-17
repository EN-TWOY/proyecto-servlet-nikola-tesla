package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ContratoDTO;
import services.ContratoService;

@WebServlet("/ServletContrato")
public class ServletContrato extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContratoService serviContrato = new ContratoService();

    public ServletContrato() {
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
		request.setAttribute("data", serviContrato.listaContratos());
		request.getRequestDispatcher("listarContratos.jsp").forward(request, response);
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("cod"));
		request.setAttribute("contrato", serviContrato.buscaCodigo(cod));
		request.getRequestDispatcher("actualizarContrato.jsp").forward(request, response);
		
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fecha = request.getParameter("txtFec");
		int admin = Integer.parseInt(request.getParameter("cboAdm"));
		int profe = Integer.parseInt(request.getParameter("cboPro"));
		double sueldo = Double.parseDouble(request.getParameter("txtSue"));
		
		ContratoDTO obj = new ContratoDTO();
		obj.setFecContrato(fecha);
		obj.setCodAdministrador(admin);
		obj.setCodProfesor(profe);
		obj.setSueContrato(sueldo);
		
		int res = serviContrato.registraContrato(obj);
		
		obj.getCodContrato();
		
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "Un nuevo contrato de profesor fue registrado en la siguiente fecha: " + obj.getFecContrato());
		}
		else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error al registrar nuevo contrato");
		}
		listar(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("txtCod"));
		String fecha = request.getParameter("txtFec");
		int admin = Integer.parseInt(request.getParameter("cboAdm"));
		int profe = Integer.parseInt(request.getParameter("cboPro"));
		double sueldo = Double.parseDouble(request.getParameter("txtSue"));
		
		ContratoDTO obj = new ContratoDTO();
		obj.setCodContrato(cod);
		obj.setFecContrato(fecha);
		obj.setCodAdministrador(admin);
		obj.setCodProfesor(profe);
		obj.setSueContrato(sueldo);
		
		int res = serviContrato.actualizaContrato(obj);
		
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "El contrato con ID: " + obj.getCodContrato() + " ha sido actualizado");
		}
		else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error al actualizar contrato");
		}
		listar(request, response);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("cod"));	
		ContratoDTO obj = new ContratoDTO();
		obj.setCodContrato(cod);
		
		int res = serviContrato.eliminaContrato(cod);
		
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "El contrato con ID: " + obj.getCodContrato() + " ha sido eliminado");
		}
		else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error al eliminar contrato");
		}
		listar(request, response);
	}
}
