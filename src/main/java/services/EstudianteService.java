package services;

import interfaces.EstudianteDAO;
import beans.EstudianteDTO;
import java.util.List;
import dao.DAOFactory;
import utils.Constantes;

public class EstudianteService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGEN_DE_DATOS_MYSQL);
	EstudianteDAO objdao = fabrica.getEstudiante();

	public List<EstudianteDTO> listaEstudiantes() {
		return objdao.listarEstudiantes();
	}

	public EstudianteDTO buscaCodigo(int cod) {
		return objdao.buscasCodigo(cod);
	}

	public int registraEstudiante(EstudianteDTO obj) {
		return objdao.registrarEstudiante(obj);
	}

	public int actualizaEstudiante(EstudianteDTO obj) {
		return objdao.actualizarEstudiante(obj);
	}

	public int eliminaEstudiante(int cod) {
		return objdao.eliminarEstudiante(cod);
	}
}
