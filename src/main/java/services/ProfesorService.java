package services;

import java.util.List;

import beans.ProfesorDTO;
import dao.DAOFactory;
import interfaces.ProfesorDAO;
import utils.Constantes;

public class ProfesorService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGEN_DE_DATOS_MYSQL);
	ProfesorDAO objpro = fabrica.getProfesor();

	public List<ProfesorDTO> listaProfesores() {
		return objpro.listarProfesores();
	}

	public ProfesorDTO buscaCodigo(int cod) {
		return objpro.buscarCodigo(cod);
	}

	public int registraProfesor(ProfesorDTO obj) {
		return objpro.registrarProfesor(obj);
	}

	public int actualizaProfesor(ProfesorDTO obj) {
		return objpro.actualizarProfesor(obj);
	}

	public int eliminaProfesor(int cod) {
		return objpro.eliminarProfesor(cod);
	}
}
