package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MateriaDTO;
import services.MateriaService;

@WebServlet("/ServletMateria")
public class ServletMateria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MateriaService serviceMateria = new MateriaService();

    public ServletMateria() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// variable
		String tipo = request.getParameter("tipo");

		if (tipo.equals("listar")) {
			listar(request, response);
		} else if (tipo.equals("buscar")){
			buscar(request, response);
		} else if (tipo.equals("actualizar")) {
			actualizar(request, response);
		} else if (tipo.equals("registrar")) {
			registrar(request, response);
		} else if (tipo.equals("eliminar")) {
			eliminar(request, response);
		} else if (tipo.equals("llenar")) {
			llenar(request, response);
		}
	}

	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("data", serviceMateria.listarMateria());
		request.getRequestDispatcher("listarMaterias.jsp").forward(request, response);
	}
	
	protected void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("cod"));
		request.setAttribute("modalidades", serviceMateria.listaModalidad());
		request.setAttribute("materia", serviceMateria.buscarMateria(cod));
		request.getRequestDispatcher("actualizarMateria.jsp").forward(request, response);
		
	}
	
	protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("txtCod"));
		String descripcion = request.getParameter("txtDes");
		int mod = Integer.parseInt(request.getParameter("cboModalidad"));
		MateriaDTO obj = new MateriaDTO();
		obj.setIdMateria(cod);
		obj.setDesMateria(descripcion);
		obj.setIdModalidad(mod);
		
		int res = serviceMateria.actualizarMateria(obj);
		if(res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "Los datos del codigo " + obj.getIdMateria() + " se actualizaron correctamente.");
		} else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error en la actualizacion de la materia");
		}
		listar(request, response);
	}
	
	protected void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String descripcion = request.getParameter("txtDescripcion").toUpperCase();
		int idModalidad = Integer.parseInt(request.getParameter("cboModalidad"));
		
		MateriaDTO objMateria = new MateriaDTO();
		objMateria.setDesMateria(descripcion);
		objMateria.setIdModalidad(idModalidad);
		
		int res = serviceMateria.registrarMateria(objMateria);
		if (res > 0) {
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "La materia "+ objMateria.getDesMateria() + " se registro correctamente.");
		} else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error en el registro de la materia");
		}
		listar(request, response);
	}
	
	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("cod"));
		
		MateriaDTO objMateria = new MateriaDTO();
		objMateria.setIdMateria(cod);
		
		int res = serviceMateria.eliminarMateria(cod);
		if (res > 0){
			request.setAttribute("tipoMensaje", "success");
			request.setAttribute("mensaje", "los datos de la materia ID: " + objMateria.getIdMateria() + " se eliminaron correctamente.");
		} else {
			request.setAttribute("tipoMensaje", "danger");
			request.setAttribute("mensaje", "Ocurrio un error en la eliminacion de la materia.");
		}
		listar(request, response);
	}
	
	protected void llenar(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		request.setAttribute("modalidades", serviceMateria.listaModalidad());
		request.getRequestDispatcher("registrarMateria.jsp").forward(request, response);
	}

}
